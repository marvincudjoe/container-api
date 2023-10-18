package com.pie.container.api.controllers

import com.pie.container.api.model.DefaultResponse
import com.pie.container.api.model.response
import com.pie.container.api.service.SystemServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * @see [com.pie.container.api.utils.DockerEngineApiReferences.System]
 */
@RestController
@RequestMapping("daemon")
class SystemController(private val systemService: SystemServiceImpl) {

    @RequestMapping(value = ["_ping"], method = [RequestMethod.HEAD])
    fun ping() {
        systemService.pingDaemon()
    }

    @GetMapping("version")
    fun daemonVersion(): ResponseEntity<DefaultResponse> {
        return response {
            systemService.version()
        }
    }
}
