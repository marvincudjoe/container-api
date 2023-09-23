package com.pie.container.api.controllers

import SystemServiceImpl
import com.pie.container.api.service.SystemService
import io.swagger.v3.oas.annotations.Hidden
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("daemon/")
class SystemController {

    private val systemService: SystemService = SystemServiceImpl()

    @RequestMapping(value = ["_ping"], method = [RequestMethod.HEAD])
    fun ping() {
        systemService.pingDaemon()
    }

    @GetMapping("apiVersion")
    fun daemonApiVersion() {
        systemService.apiVersion()
    }
}

@Hidden
@RestController
@RequestMapping("/")
class RootController {
    @GetMapping
    fun welcome(): String = "Welcome to container-api"
}
