FROM eclipse-temurin:20-jdk-alpine
LABEL com.pie.container.api.author="Marvin"

ARG JAR_FILE=./build/libs/container-api.jar
ENV APP_HOME=/app
WORKDIR $APP_HOME

COPY $JAR_FILE $APP_HOME/app.jar

EXPOSE 8080
CMD ["java","-jar","app.jar"]
