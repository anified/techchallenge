FROM openjdk:12.0.2-jdk
LABEL maintainer="gohkengan@hotmail.com"
LABEL description="Tech Challenge"
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} techchallenge-be.jar
ENTRYPOINT ["java","-jar","/techchallenge-be.jar"]