FROM amazoncorretto: 17-alpine-jdk
MAINTAINER JZ
COPY target/JZ-0.0.1-SNAPSHOT.jar jz-app.jar
ENTRYPOINT ["java","-jar","/jz-app.jar"]