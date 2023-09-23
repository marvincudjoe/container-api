package com.pie.container.api.service

import com.github.dockerjava.core.DefaultDockerClientConfig
import com.github.dockerjava.core.DockerClientConfig
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient
import com.github.dockerjava.transport.DockerHttpClient
import com.pie.container.api.RequestException
import com.pie.container.api.utils.logger
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

    fun sendRequest(req: DockerHttpClient.Request): DockerHttpClient.Response {
        logger.info("Request: ${req.method()} ${req.path()}")
        return runCatching {
            // todo: add timeout
            httpClient.execute(req)
        }.onSuccess {
            return it.apply {
                logger.info ("Response: is $statusCode")
            }
        }.onFailure { e ->
            if (e is RuntimeException) {
                throw RequestException("Docker may not be daemon available: ${e.message}")
            } else {
                throw RequestException("Failed to send request. \n${e.message}, $e")
            }
        }.getOrThrow()
    }
}
