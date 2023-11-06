package com.pie.container.api.controllers

import com.pie.container.api.model.DefaultResponse
import com.pie.container.api.model.response
import com.pie.container.api.service.NetworksService
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("networks")
class NetworkController(private val networksService: NetworksService) {

    @GetMapping
    fun listNetworks(
        @Parameter(
            description = "JSON encoded value of the filters" +
                    " (a map[string][]string) to process on the networks list."
        )
        @RequestParam(required = false, defaultValue = "") filters: String
    ): ResponseEntity<DefaultResponse> = response { networksService.listNetworks(filters) }

    @GetMapping("/{id}")
    fun inspectANetwork(
        @Parameter(description = "Network ID or name")
        @PathVariable id: String,
        @Parameter(description = "Detailed inspect output for troubleshooting")
        @RequestParam(required = false, defaultValue = "false") verbose: Boolean,
        @Parameter(description = "Filter the network by scope (swarm, global, or local)")
        @RequestParam(required = false, defaultValue = "") scope: String
    ): ResponseEntity<DefaultResponse> =
        response { networksService.inspectANetwork(id, verbose, scope) }
}
