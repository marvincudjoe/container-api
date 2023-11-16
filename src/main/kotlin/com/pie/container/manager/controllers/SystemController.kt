package com.pie.container.manager.controllers

import com.pie.container.manager.model.DefaultResponse
import com.pie.container.manager.model.response
import com.pie.container.manager.service.SystemServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * @see [com.pie.container.manager.utils.DockerEngineApiReferences.System]
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
