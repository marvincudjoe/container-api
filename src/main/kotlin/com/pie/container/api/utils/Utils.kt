package com.pie.container.api.utils

import com.github.dockerjava.transport.DockerHttpClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.InputStream

fun setGetRequest(path: String): DockerHttpClient.Request {
    return DockerHttpClient.Request.builder().method(DockerHttpClient.Request.Method.GET).path(path).build()
}

fun InputStream.readMessage() = (this.bufferedReader().use { it.readText() })

inline val <reified T> T.logger: Logger
    get() = LoggerFactory.getLogger(T::class.java)
