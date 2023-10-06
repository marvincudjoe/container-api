package com.pie.container.api.service

import com.pie.container.api.model.DefaultResponse
import com.pie.container.api.utils.setGetRequest
import org.springframework.stereotype.Service

@Service
class ContainersServiceImpl : ContainersService {
    private var daemonService = DaemonServiceImpl()
    override fun listContainers(
        all: Boolean,
        limit: Int,
        size: Boolean,
        filters: String
    ): DefaultResponse {
        return daemonService.sendRequest(
            setGetRequest("containers/json?all=$all&limit=$limit&size=$size&filters=$filters")
        )
    }
}
