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

    override fun stopContainer(id: String, signal: String, t: Int): DefaultResponse {
        return daemonService.sendRequest(
            setPostRequest("containers/$id/stop?signal=$signal&t=$t"),
            DockerEngineApiReferences.Containers.STOP
        )
    }
}
