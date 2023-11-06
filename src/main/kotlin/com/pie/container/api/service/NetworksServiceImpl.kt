package com.pie.container.api.service

import com.pie.container.api.model.DefaultResponse
import com.pie.container.api.utils.DockerEngineApiReferences
import com.pie.container.api.utils.setGetRequest
import org.springframework.stereotype.Service

@Service
class NetworksServiceImpl : NetworksService {

    private var daemonService = DaemonServiceImpl()

    override fun listNetworks(filters: String): DefaultResponse {
        return daemonService.sendRequest(
            setGetRequest("networks?filters=$filters"),
            DockerEngineApiReferences.Networks.NETWORK_LIST
        )
    }

    override fun inspectANetwork(id: String, verbose: Boolean, scope: String): DefaultResponse {
        return daemonService.sendRequest(
            setGetRequest("networks/$id?verbose=$verbose&scope$scope"),
            DockerEngineApiReferences.Networks.NETWORK_INSPECT
        )
    }
}
