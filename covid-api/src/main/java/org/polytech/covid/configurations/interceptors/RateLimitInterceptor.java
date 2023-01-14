package org.polytech.covid.configurations.interceptors;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.polytech.covid.entities.User;
import org.polytech.covid.repositories.UserRepository;
import org.polytech.covid.services.RateLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;

public class RateLimitInterceptor implements HandlerInterceptor{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RateLimitService rateLimitService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> currentUser = userRepository.findByEmail(auth.getName());
        Long id = currentUser.isPresent() ? currentUser.get().getId() : -1;

        Bucket bucket = rateLimitService.resolveBucket(id);
        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);

        if(probe.isConsumed()) {
            response.addHeader("X-Rate-Limit-Remaining", String.valueOf(probe.getRemainingTokens()));
            return true;
        }

        response.addHeader("X-Rate-Limit-Retry-After-Seconds", String.valueOf(probe.getNanosToWaitForRefill() * 0.000000001));
        response.sendError(HttpStatus.TOO_MANY_REQUESTS.value(), "API request quota exhausted");
        return false;
    }
}
