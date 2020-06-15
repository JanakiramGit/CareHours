# Baase image containing Java runtime

FROM java:8

# Expose Port 8080

EXPOSE 8080

ADD target/CarHours.jar CareHours.jar

ENTRYPOINT ["java", "-jar", "CareHours.jar"]