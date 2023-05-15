FROM amazoncorretto: 11-alpine-jdk
MAINTAINER JZ
COPY target/JZ-0.0.1-SNAPSHOT.jar jz-app.jar
ENTRYPOINT ["java","-jar","/JZ-app.jar"]