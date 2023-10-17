package com.pie.container.api.service

import com.github.dockerjava.core.DefaultDockerClientConfig
import com.github.dockerjava.core.DockerClientConfig
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient
import com.github.dockerjava.transport.DockerHttpClient
import com.pie.container.api.model.DefaultResponse
import com.pie.container.api.utils.logger
import com.pie.container.api.utils.readMessage
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class DaemonServiceImpl {

    private final val config: DockerClientConfig
    private final val httpClient: ApacheDockerHttpClient

    init {
        config = DefaultDockerClientConfig.createDefaultConfigBuilder().build()
        httpClient = ApacheDockerHttpClient.Builder()
            .dockerHost(config.dockerHost)
            .build()
    }

    fun sendRequest(req: DockerHttpClient.Request): DefaultResponse {
        logger.info("Request: ${req.method()} ${req.path()}")
        var response = DefaultResponse()
        runCatching {
            // todo: add timeout
            httpClient.execute(req).apply {
                response = DefaultResponse(HttpStatus.valueOf(statusCode), body.readMessage())
            }
        }.onFailure { ex ->
            response = if (ex is RuntimeException) {
                logger.error("Caught ${ex.javaClass}: ${ex.stackTrace}")
                DefaultResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Docker daemon may not be available: ${ex.message}")
            } else {
                logger.error("Caught ${ex.javaClass}: ${ex.stackTrace}")
                DefaultResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to send request. \n${ex.message}")
            }
        }
        return response
    }
}
