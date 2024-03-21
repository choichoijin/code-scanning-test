FROM openjdk:23-ea-14-jdk-oraclelinux9
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","/app.jar"]