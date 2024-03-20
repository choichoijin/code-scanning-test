FROM 8.6.0-jdk17
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]