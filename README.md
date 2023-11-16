# Container API

[RESTful](https://en.wikipedia.org/wiki/REST) web application with a collection of API endpoints for managing Docker containers.

## Pre-requisites
- [JDK 17](https://adoptium.net/en-GB/temurin/releases/)
- [Docker](https://docs.docker.com/get-docker/)

## Running the application
This project uses [Gradle](https://gradle.org/) as the build tool. 
To run the application, execute one of the following commands:

On Linux:
```shell
./gradlew bootRun
```

On Windows:
```shell
gradlew.bat bootRun
```

As a Docker container (currently requires the jar to be pre-built):
```shell
```shell
docker build --no-cache --tag container-api . &&  docker run --rm -d --publish 8080:8080 --name container-api container-api
```

## Notes:
Docker provides an API for interacting with the Docker Daemon, 
called the [Docker Engine API](https://docs.docker.com/engine/api/).

This project uses unofficial SDK, [docker-java](https://github.com/docker-java/docker-java),
to interact with the Docker Engine API.

### Example call
The Docker Engine API is accessible by an HTTP client such as curl.

Example call on Linux:

```shell
curl -v --unix-socket /var/run/docker.sock "http://localhost/v1.43/_ping"
```

### Limitations
1. This project depends on an unofficial
   sdk, [docker-java](https://github.com/docker-java/docker-java),
   which looks to be actively supported but not actively maintained
2. Some may see this application as a way to create on demand containers.
   - This is not the intended purpose.
   - This application must not be used as is. Better solutions exist.
     See [Testcontainer](https://testcontainers.com/) and [ContainerSSH](https://containerssh.io/).
3. This is a work in progress to implement what I learn.
    - I *may* add updates as I progress in studying Kotlin and other development practices.
