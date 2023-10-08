# Container API

[RESTful](https://en.wikipedia.org/wiki/REST)
web app with a collection of API endpoints for managing Docker containers.

## Pre-requisites

- [JDK 20](https://adoptium.net/en-GB/temurin/releases/)
- [Docker](https://docs.docker.com/get-docker/)

## Running the application

This project uses [Gradle](https://gradle.org/) as the build tool. To run the application, execute
the following command:

On Linux: execute the following command:

```shell
./gradlew bootRun
```

On Windows, use the following command:

```shell
gradlew.bat bootRun
```

## Notes:

Docker provides an API for interacting with the Docker Daemon, called
the [Docker Engine API](https://docs.docker.com/engine/api/).
The Docker Engine API is a RESTful API accessed by an HTTP client such as curl.

Example call on Linux:

```shell
curl -v --unix-socket /var/run/docker.sock "http://localhost/v1.43/_ping"
```

This project replicates the [Docker API reference](https://docs.docker.com/engine/api/latest/) using
an unofficial SDK, [docker-java](https://github.com/docker-java/docker-java)

### Purpose

This project is intended to be a learning experience for Kotlin, Spring Boot, and Docker.
The goal is to create a collection of API endpoints that aid in managing containers.

This is a work in progress. I *may* add updates as I learn more about Spring Boot and Kotlin.

### Limitations

1. This project depends on an unofficial
   sdk, [docker-java](https://github.com/docker-java/docker-java),
   which looks to be actively supported but not actively maintained
2. Some may see this application as a way to create on demand containers.
   - This is not the intended purpose.
   - This app must not be used in a production setting.
     See [Testcontainer](https://testcontainers.com/) and [ContainerSSH](https://containerssh.io/).
