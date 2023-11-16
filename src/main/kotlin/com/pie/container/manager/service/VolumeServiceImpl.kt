package com.pie.container.manager.service

import com.pie.container.manager.model.DefaultResponse
import com.pie.container.manager.utils.DockerEngineApiReferences
import com.pie.container.manager.utils.setGetRequest
import org.springframework.stereotype.Service

@Service
class VolumeServiceImpl: VolumeService {

    private var daemonService = DaemonServiceImpl()

    override fun listVolumes(filters: String): DefaultResponse {
        return daemonService.sendRequest(
            setGetRequest("volumes?filters=$filters"),
            DockerEngineApiReferences.Volumes.LIST
        )
    }

    override fun inspectVolume(name: String): DefaultResponse {
        return daemonService.sendRequest(
            setGetRequest("volumes/$name"),
            DockerEngineApiReferences.Volumes.INSPECT
        )
    }

    override fun removeVolume(name: String, force: Boolean): DefaultResponse {
        return daemonService.sendRequest(
            setGetRequest("volumes/$name"),
            DockerEngineApiReferences.Volumes.DELETE
        )
    }

    override fun deleteUnusedVolumes(filters: String): DefaultResponse {
        return daemonService.sendRequest(
            setGetRequest("volumes/prune"),
            DockerEngineApiReferences.Volumes.PRUNE
        )
    }
}