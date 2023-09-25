package com.pie.container.api.controllers

import com.pie.container.api.service.SystemService
import com.pie.container.api.service.SystemServiceImpl
import io.swagger.v3.oas.annotations.Hidden
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("daemon")
class SystemController {

    private val systemService = SystemServiceImpl()

    @RequestMapping(value = ["_ping"], method = [RequestMethod.HEAD])
    fun ping() {
        systemService.pingDaemon()
    }

    @GetMapping("version")
    fun daemonVersion() {
        systemService.version()
    }
}

@Hidden
@RestController
@RequestMapping("/")
class RootController {
    @GetMapping
    fun welcome(): String = "Welcome to container-api"
}
