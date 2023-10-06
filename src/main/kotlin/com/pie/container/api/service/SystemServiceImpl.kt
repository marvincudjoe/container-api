package com.pie.container.api.service

import com.pie.container.api.model.DefaultResponse
import com.pie.container.api.utils.setGetRequest
import org.springframework.stereotype.Service

@Service
class SystemServiceImpl : SystemService {

    private var daemonService = DaemonServiceImpl()

    override fun pingDaemon() {
        daemonService.sendRequest(setGetRequest("_ping"))
    }

    override fun version(): DefaultResponse {
        return daemonService.sendRequest(setGetRequest("version"))
    }
}
