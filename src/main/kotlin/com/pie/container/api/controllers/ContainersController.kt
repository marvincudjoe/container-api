package com.pie.container.api.controllers

import com.pie.container.api.model.endpointNotImplemented
import com.pie.container.api.model.response
import com.pie.container.api.service.ContainersServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("containers")
class ContainersController {
    private val containersService = ContainersServiceImpl()

    @GetMapping("json")
    fun listContainers(
        @RequestParam(required = false, defaultValue = "false") all: Boolean,
        @RequestParam(required = false, defaultValue = "0") limit: Int,
        @RequestParam(required = false, defaultValue = "false") size: Boolean,
        @RequestParam(required = false, defaultValue = "") filters: String
    ): ResponseEntity<String> {
        return response {
            containersService.listContainers(all, limit, size, filters)
        }
    }

    @PostMapping("create")
    fun createContainer(): ResponseEntity<String> {
        // todo: implement
        return endpointNotImplemented
    }

    @PostMapping("{id}/start")
    fun startContainer(): ResponseEntity<String> {
        // todo: implement
        return endpointNotImplemented
    }

    @PostMapping("{id}/stop")
    fun stopContainer(@PathVariable id: String): ResponseEntity<String> {
        return response {
            containersService.stopContainer(id)
        }
    }

    @PostMapping("{id}/restart")
    fun restartContainer(): ResponseEntity<String> {
        // todo: implement
        return endpointNotImplemented
    }
}
