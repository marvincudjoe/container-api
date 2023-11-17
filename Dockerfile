# Use the official Gradle image as the base image
FROM gradle:8.4-jdk17-alpine AS build

WORKDIR /app

COPY build.gradle.kts settings.gradle.kts ./

COPY src ./src

RUN gradle bootJar --no-daemon

FROM bellsoft/liberica-runtime-container:jdk-17-slim-musl
LABEL com.pie.container.manager.author="Marvin"

WORKDIR /app

COPY --from=build /app/build/libs/container-manager.jar ./

CMD ["java", "-jar", "container-manager.jar"]