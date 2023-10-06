package com.pie.container.api.service

import com.pie.container.api.model.DefaultResponse

interface ContainersService {
    fun listContainers(all: Boolean, limit: Int, size: Boolean, filters: String): DefaultResponse
}
