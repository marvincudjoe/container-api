package com.pie.container.api.service

import com.github.dockerjava.transport.DockerHttpClient
import com.pie.container.api.model.DefaultResponse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus

@ExtendWith(MockitoExtension::class)
class ContainersServiceImplTest {
    @Mock
    lateinit var daemonServiceImpl: DaemonServiceImpl

    @InjectMocks
    private val containersService = ContainersServiceImpl()

    @Test
    fun listContainers() {
        Mockito.`when`(daemonServiceImpl.sendRequest(setGetRequest(setPath(true, 10, true, ""))))
            .thenReturn(DefaultResponse(HttpStatus.ACCEPTED, "[]"))
        val result = containersService.listContainers(true, 10, true, "")
        val expectedResponse = DefaultResponse(HttpStatus.ACCEPTED, "[]")
        assertEquals(expectedResponse, result)
    }

    private fun setPath(all: Boolean, limit: Int, size: Boolean, filters: String): String {
        return "containers/json?all=$all&limit=$limit&size=$size&filters=$filters"
    }

    private fun setGetRequest(path: String): DockerHttpClient.Request {
        return DockerHttpClient.Request.builder().method(DockerHttpClient.Request.Method.GET)
            .path("/$path").build()
    }
}