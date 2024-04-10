package com.pie.container.manager.service

import com.fasterxml.jackson.databind.JsonNode
import com.pie.container.manager.model.DefaultResponse

interface ContainersService {
    fun listContainers(all: Boolean, limit: Int, size: Boolean, filters: String): DefaultResponse
    fun createContainer(payload: JsonNode): DefaultResponse
    fun inspectContainer(id: String, size: Boolean): DefaultResponse
    fun stopContainer(id: String, signal: String, t: Int): DefaultResponse
    fun startContainer(id: String, detachKeys: String): DefaultResponse
    fun restartContainer(id: String, signal: String, t: Int): DefaultResponse
    fun deleteStoppedContainers(filters: String): DefaultResponse
}
