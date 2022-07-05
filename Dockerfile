FROM openjdk:12.0.2-jdk
LABEL maintainer="gohkengan@hotmail.com"
LABEL description="Tech Challenge"
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} techchallenge.jar
ENTRYPOINT ["java","-jar","/techchallenge.jar"]