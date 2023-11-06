package com.pie.container.api.service

import com.pie.container.api.model.DefaultResponse

interface NetworksService {
    fun listNetworks(filters: String): DefaultResponse
    fun inspectANetwork(id: String, verbose: Boolean, scope: String): DefaultResponse
}
