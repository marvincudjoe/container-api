package com.pie.container.api.utils

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.dockerjava.transport.DockerHttpClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.InputStream

fun setGetRequest(path: String): DockerHttpClient.Request {
    return DockerHttpClient.Request.builder().method(DockerHttpClient.Request.Method.GET)
        .path("/$path").build()
}

fun setPostRequest(path: String): DockerHttpClient.Request {
    return DockerHttpClient.Request.builder().method(DockerHttpClient.Request.Method.POST)
        .path("/$path").build()
}

private fun InputStream.readMessage() = (this.bufferedReader().use { it.readText() })

fun InputStream.toJson(): JsonNode = ObjectMapper().readTree(readMessage())

inline val <reified T> T.logger: Logger
    get() = LoggerFactory.getLogger(T::class.java)
