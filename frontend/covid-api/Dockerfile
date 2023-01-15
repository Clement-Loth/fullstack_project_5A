### STAGE 1: Build ###
FROM node:16.18-alpine AS build
WORKDIR /usr/src/app
COPY ./frontend/covid-api .
RUN npm install
RUN npm run build
### STAGE 2: Run ###
FROM nginx:1.17.1-alpine
###COPY --from=build /usr/src/app/nginx.conf /etc/nginx/nginx.conf
COPY --from=build /usr/src/app/dist/covid-api /usr/share/nginx/html