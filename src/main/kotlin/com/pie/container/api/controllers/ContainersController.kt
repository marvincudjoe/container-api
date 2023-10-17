package com.pie.container.api.controllers

import com.pie.container.api.model.DefaultResponse
import com.pie.container.api.model.endpointNotImplemented
import com.pie.container.api.model.response
import com.pie.container.api.service.ContainersServiceImpl
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * @see [com.pie.container.api.utils.DockerEngineApiReferences.Containers]
 */
@RestController
@RequestMapping("containers")
class ContainersController {

    @Autowired
    private val containersService = ContainersServiceImpl()

    @GetMapping("json")
    fun listContainers(
        @Parameter(description = "Return all containers.")
        @RequestParam(required = false, defaultValue = "false") all: Boolean,
        @Parameter(description = "Return this number of most recently created containers, including non-running ones.")
        @RequestParam(required = false, defaultValue = "0") limit: Int,
        @Parameter(description = "Return the size of container as fields SizeRw and SizeRootFs.")
        @RequestParam(required = false, defaultValue = "false") size: Boolean,
        @Parameter(description = "Filters to process on the container list, encoded as JSON (a map[string][]string).")
        @RequestParam(required = false, defaultValue = "") filters: String,
    ): ResponseEntity<DefaultResponse> {
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
    fun stopContainer(
        @Parameter(description = "Name or Hash of the container to stop.")
        @PathVariable id: String,
        @Parameter(description = "Signal to send to the container as an integer or string (e.g. SIGINT).")
        @RequestParam(required = false, defaultValue = "SIGKILL") signal: String,
        @Parameter(description = "Number of seconds to wait before killing the container.")
        @RequestParam(required = false, defaultValue = "0") t: Int
    ): ResponseEntity<DefaultResponse> {
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
