package com.pie.container.api.utils

private const val DOCKER_API_REF = "https://docs.docker.com/engine/api/1.43/"

class DockerEngineApiReferences {
    /**
     * See [Containers](https://docs.docker.com/engine/api/v1.43/#tag/Container)
     */
    object Containers {
        const val LIST = "$DOCKER_API_REF/#tag/operation/ContainerList"
        const val START = "$DOCKER_API_REF/#tag/operation/ContainerStart"
        const val STOP = "$DOCKER_API_REF/#tag/operation/ContainerStop"
        const val RESTART = "$DOCKER_API_REF/#tag/operation/ContainerRestart"
    }

    /**
     * See [System](https://docs.docker.com/engine/api/v1.43/#tag/System)
     */
    object System {
        const val PING_HEAD = "$DOCKER_API_REF/#tag/System/operation/SystemPingHead"
        const val VERSION = "$DOCKER_API_REF/#tag/System/operation/SystemVersion"
    }
}
