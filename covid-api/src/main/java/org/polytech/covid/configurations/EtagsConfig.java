package org.polytech.covid.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

@Configuration
public class EtagsConfig {

    @Bean
    public ShallowEtagHeaderFilter shallowEtagHeaderFilter() { return new ShallowEtagHeaderFilter(); }
}