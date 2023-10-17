package com.pie.container.api.controllers

import com.pie.container.api.model.endpointNotImplemented
import com.pie.container.api.model.response
import com.pie.container.api.service.ContainersServiceImpl
import io.swagger.v3.oas.annotations.Parameter
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

    /**
     * @see <a href="https://docs.docker.com/engine/api/v1.43/#tag/Container/operation/ContainerStopp">Stop a container</a>
     * @see <a href="https://docs.docker.com/engine/reference/commandline/kill/#send-a-kill-signal-to-a-container">docker kill</a>
     */
    @PostMapping("{id}/stop")
    fun stopContainer(@PathVariable id: String,
                      @Parameter(description = "Signal to send to the container as an integer or string (e.g. SIGINT).")
                      @RequestParam(required = false, defaultValue = "SIGKILL") signal: String,
                      @Parameter(description = "Number of seconds to wait before killing the container.")
                      @RequestParam(required = false, defaultValue = "0") t: Int): ResponseEntity<String> {
        return response {
            containersService.stopContainer(id, signal, t)
        }
    }

    @PostMapping("{id}/restart")
    fun restartContainer(): ResponseEntity<String> {
        // todo: implement
        return endpointNotImplemented
    }
}
