FROM openjdk:17
EXPOSE 8080


ADD target/weather-backend.jar weather-backend.jar
ENTRYPOINT ["java", "-jar", "/weather-backend.jar"]