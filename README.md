# Container Manager

[RESTful](https://en.wikipedia.org/wiki/REST) web application with a collection of API endpoints for managing Docker containers.

<!-- TOC -->
* [Container Manager](#container-manager)
  * [Pre-requisites](#pre-requisites)
  * [Running the application](#running-the-application)
    * [System Health Check](#system-health-check)
      * [Through the application](#through-the-application)
      * [Direct call](#direct-call)
    * [Swagger](#swagger)
    * [Available Actuator Endpoints](#available-actuator-endpoints)
  * [Notes:](#notes)
    * [Limitations](#limitations)
<!-- TOC -->

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

<details>
    <summary>Alternative: Docker</summary>
        The docker container may not interact with the Docker Daemon as expected.

        ```shell
        docker compose up
        ```
      
</details>


### System Health Check
Health Check to the Docker Engine:
#### Through the application
```shell
curl -I --head \
  'http://localhost:8080/daemon/_ping' \
  -H 'accept: */*'
```
#### Direct call
The Docker Engine API is accessible by an HTTP client such as curl.
```shell
curl -v --unix-socket /var/run/docker.sock "http://localhost/v1.43/_ping"
```
If either call fails
- Verify the Docker Daemon (or this application) is running
- Verify your docker installation made `docker.sock` accessible

### Swagger
- UI: http://localhost:8080/swagger-ui/index.html
- JSON: http://localhost:8080/v3/api-docs

### Available Actuator Endpoints
- http://localhost:8080/actuator
- http://localhost:8080/actuator/health
- http://localhost:8080/actuator/metrics

## Notes:
Docker provides an API for interacting with the Docker Daemon, 
called the [Docker Engine API](https://docs.docker.com/engine/api/).

This project uses an unofficial SDK, [docker-java](https://github.com/docker-java/docker-java),
to interact with the Docker Engine API.

### Limitations
1. This project depends on an unofficial
   sdk, [docker-java](https://github.com/docker-java/docker-java),
   which looks to be actively supported but not actively maintained
2. Some may see this application as a way to create on demand containers.
   - This is not the intended purpose.
   - This application must not be used as is. Better solutions exist.
     See [Testcontainers](https://testcontainers.com/) and [ContainerSSH](https://containerssh.io/).
3. This is a work in progress to implement what I learn.
    - I *may* add updates as I progress in studying Kotlin and other development practices.
