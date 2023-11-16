FROM eclipse-temurin:17-jdk-alpine
LABEL com.pie.container.manager.author="Marvin"

# TODO: This should not be reliant on an existing jar file
ARG JAR_FILE=./build/libs/container-api.jar
ENV APP_HOME=/app
WORKDIR $APP_HOME

COPY $JAR_FILE $APP_HOME/app.jar

EXPOSE 8080
CMD ["java","-jar","app.jar"]
