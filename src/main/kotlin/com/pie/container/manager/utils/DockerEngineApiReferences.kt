package com.pie.container.manager.utils

private const val DOCKER_API_REF = "https://docs.docker.com/engine/api/1.43/"

class DockerEngineApiReferences {
    /**
     * See [Containers](https://docs.docker.com/engine/api/v1.43/#tag/Container)
     */
    object Containers {
        const val LIST = "$DOCKER_API_REF/#tag/operation/ContainerList"
        const val INSPECT = "$DOCKER_API_REF/#tag/operation/ContainerList"
        const val START = "$DOCKER_API_REF/#tag/operation/ContainerStart"
        const val STOP = "$DOCKER_API_REF/#tag/operation/ContainerStop"
        const val RESTART = "$DOCKER_API_REF/#tag/operation/ContainerRestart"
        const val CONTAINER_PRUNE = "$DOCKER_API_REF/#tag/operation/ContainerPrune"
    }

    /**
     * See [Networks](https://docs.docker.com/engine/api/v1.43/#tag/Network/operation/NetworkList)
     */
    object Networks {
        const val NETWORK_LIST = "$DOCKER_API_REF/#tag/operation/NetworkList"
        const val NETWORK_INSPECT = "$DOCKER_API_REF/#tag/operation/NetworkInspect"
    }

    /**
     * See [Volumes](https://docs.docker.com/engine/api/v1.43/#tag/Volume)
     */
    object Volumes {
        const val LIST = "$DOCKER_API_REF/#tag/operation/VolumeList"
        const val INSPECT = "$DOCKER_API_REF/#tag/operation/VolumeInspect"
        const val DELETE = "$DOCKER_API_REF/#tag/operation/VolumesDelete"
        const val PRUNE = "$DOCKER_API_REF/#tag/operation/VolumePrune"
    }

    /**
     * See [System](https://docs.docker.com/engine/api/v1.43/#tag/System)
     */
    object System {
        const val PING_HEAD = "$DOCKER_API_REF/#tag/System/operation/SystemPingHead"
        const val VERSION = "$DOCKER_API_REF/#tag/System/operation/SystemVersion"
    }
}
