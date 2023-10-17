package com.pie.container.api.service

import com.pie.container.api.model.DefaultResponse
import com.pie.container.api.utils.DockerEngineApiReferences
import com.pie.container.api.utils.setGetRequest
import org.springframework.stereotype.Service

/**
 * @see SystemService
 */
@Service
class SystemServiceImpl : SystemService {

    private var daemonService = DaemonServiceImpl()

    override fun pingDaemon() {
        daemonService.sendRequest(
            setGetRequest("_ping"),
            DockerEngineApiReferences.System.PING_HEAD
        )
    }

    override fun version(): DefaultResponse {
        return daemonService.sendRequest(
            setGetRequest("version"),
            DockerEngineApiReferences.System.VERSION,
        )
    }
}
