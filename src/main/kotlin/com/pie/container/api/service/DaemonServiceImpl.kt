package com.pie.container.api.service

import com.github.dockerjava.core.DefaultDockerClientConfig
import com.github.dockerjava.core.DockerClientConfig
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient
import com.github.dockerjava.transport.DockerHttpClient
import com.github.dockerjava.transport.DockerHttpClient.Response
import com.pie.container.api.model.DefaultResponse
import com.pie.container.api.utils.logger
import com.pie.container.api.utils.toJson
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.net.URI

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

    fun sendRequest(req: DockerHttpClient.Request, reference: String): DefaultResponse {
        var response = DefaultResponse()
        runCatching {
            // todo: add timeout
            httpClient.execute(req).apply {
                if (HttpStatus.valueOf(this.statusCode).isError) {
                    logger.error("Failed request to ${req.path()}")
                }
                response = handelResponseStatus(this, URI(reference))
            }
        }.onFailure { ex ->
            response = if (ex is RuntimeException) {
                logger.error("Caught ${ex.javaClass}: ${ex.stackTrace}")
                DefaultResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    body = "Caught RuntimeException: ${ex.message}"
                )
            } else {
                logger.error("Caught ${ex.javaClass}: ${ex.stackTrace}")
                DefaultResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    body = "Failed to send request. \n${ex.message}"
                )
            }
        }
        return response
    }

    fun handelResponseStatus(response: Response, dockerApiReference: URI): DefaultResponse {
        val status = HttpStatus.valueOf(response.statusCode)
        val body = response.body.toJson()
        return DefaultResponse(status, dockerApiReference, body)
    }
}
