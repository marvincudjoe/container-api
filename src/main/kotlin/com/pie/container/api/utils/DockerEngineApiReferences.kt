package com.pie.container.api.utils

private const val DOCKER_API_REF = "https://docs.docker.com/engine/api/1.43/"

class DockerEngineApiReferences {
    object Containers {
        // https://docs.docker.com/engine/api/v1.43/#tag/Container
        const val LIST = "$DOCKER_API_REF/#tag/operation/ContainerList"
        const val STOP = "$DOCKER_API_REF/#tag/operation/ContainerStop"
    }

    object System {
        // https://docs.docker.com/engine/api/v1.43/#tag/System
        const val PING_HEAD = "$DOCKER_API_REF/#tag/System/operation/SystemPingHead"
        const val VERSION = "$DOCKER_API_REF/#tag/System/operation/SystemVersion"
    }
}
