package com.pie.container.manager.service.impl

import com.github.dockerjava.core.DefaultDockerClientConfig
import com.github.dockerjava.core.DockerClientConfig
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient
import com.github.dockerjava.transport.DockerHttpClient
import com.github.dockerjava.transport.DockerHttpClient.Response
import com.pie.container.manager.model.DefaultResponse
import com.pie.container.manager.utils.logger
import com.pie.container.manager.utils.toJson
import org.springframework.boot.json.JsonParseException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.net.URI
import java.time.Duration

private const val CONNECTION_TIMEOUT = 1000L // time to establish the connection with the docker daemon
private const val RESPONSE_TIMEOUT = 6000L // time waiting for a response after connecting to the daemon

@Service
class DaemonServiceImpl {
    private final val config: DockerClientConfig
    private final val httpClient: ApacheDockerHttpClient

    init {
        config = DefaultDockerClientConfig.createDefaultConfigBuilder().build()
        httpClient =
            ApacheDockerHttpClient.Builder()
                .dockerHost(config.dockerHost)
                .connectionTimeout(Duration.ofMillis(CONNECTION_TIMEOUT))
                .responseTimeout(Duration.ofMillis(RESPONSE_TIMEOUT))
                .build()
    }

    fun sendRequest(req: DockerHttpClient.Request, reference: String): DefaultResponse {
        var response = DefaultResponse()
        runCatching {
            httpClient.execute(req).apply {
                response = handelResponseStatus(this, URI(reference))
                if (response.status.isError) {
                    logger.error("Failed request: ${req.method()} ${req.path()}. Reason: ${response.body}")
                }
            }
        }.onFailure { ex ->
            logger.error("Caught ${ex.javaClass} with reason: ${ex.message}")
            response = if (ex is RuntimeException) {
                DefaultResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR, body = "RuntimeException: ${ex.message}"
                )
            } else {
                DefaultResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR, body = "Failed to send request. \n${ex.message}"
                )
            }
        }
        return response
    }

    fun handelResponseStatus(response: Response, dockerApiReference: URI): DefaultResponse {
        val status = HttpStatus.valueOf(response.statusCode)
        val body = try {
            response.body.toJson()
        } catch (_: JsonParseException) {
            logger.error("Unable to parse response: ${response.body}")
        }
        return DefaultResponse(status, dockerApiReference, body)
    }
}
