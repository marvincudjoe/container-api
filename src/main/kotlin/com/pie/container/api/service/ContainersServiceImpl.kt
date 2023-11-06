package com.pie.container.api.service

import com.pie.container.api.model.DefaultResponse
import com.pie.container.api.utils.DockerEngineApiReferences
import com.pie.container.api.utils.setGetRequest
import com.pie.container.api.utils.setPostRequest
import org.springframework.stereotype.Service

/**
 * @see ContainersService
 */
@Service
class ContainersServiceImpl : ContainersService {
    private var daemonService = DaemonServiceImpl()
    override fun listContainers(
        all: Boolean,
        limit: Int,
        size: Boolean,
        filters: String,
    ): DefaultResponse {
        return daemonService.sendRequest(
            setGetRequest("containers/json?all=$all&limit=$limit&size=$size&filters=$filters"),
            DockerEngineApiReferences.Containers.LIST
        )
    }

    override fun startContainer(id: String, detachKeys: String): DefaultResponse {
        return if (detachKeys.isEmpty()) {
            daemonService.sendRequest(
                setPostRequest("containers/$id/start"),
                DockerEngineApiReferences.Containers.START
            )
        } else {
            daemonService.sendRequest(
                setPostRequest("containers/$id/start?detachKeys=$detachKeys"),
                DockerEngineApiReferences.Containers.START
            )
        }
    }

    override fun stopContainer(id: String, signal: String, t: Int): DefaultResponse {
        return daemonService.sendRequest(
            setPostRequest("containers/$id/stop?signal=$signal&t=$t"),
            DockerEngineApiReferences.Containers.STOP
        )
    }

    override fun restartContainer(id: String, signal: String, t: Int): DefaultResponse {
        return daemonService.sendRequest(
            setPostRequest("containers/$id/restart?signal=$signal&t=$t"),
            DockerEngineApiReferences.Containers.RESTART
        )
    }

    override fun deleteStoppedContainers(filters: String): DefaultResponse {
        return daemonService.sendRequest(
            setPostRequest("containers/prune$filters"),
            DockerEngineApiReferences.Containers.CONTAINER_PRUNE
        )
    }
}
