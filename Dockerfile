# create image from base image openjdk which a linux and has Java
FROM openjdk:8-jre-alpine
# copy jar file into image
COPY target/vehicles-status-service-0.0.1-SNAPSHOT.jar /vehicles-status-service.jar 
EXPOSE 8080
# run application with this command line 
CMD ["/usr/bin/java", "-jar", "-Dspring.profiles.active=default", "/vehicles-status-service.jar"]
