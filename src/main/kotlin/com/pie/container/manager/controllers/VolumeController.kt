package com.pie.container.manager.controllers

import com.pie.container.manager.model.DefaultResponse
import com.pie.container.manager.model.response
import com.pie.container.manager.service.VolumeService
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("volumes")
class VolumeController(private val volumeService: VolumeService) {

    @GetMapping
    fun listVolumes(
        @Parameter(
            description = "JSON encoded value of the filters (a map[string][]string) to process on the volumes list."
        )
        @RequestParam(required = false, defaultValue = "") filters: String
    ): ResponseEntity<DefaultResponse> = response { volumeService.listVolumes(filters) }

    @GetMapping("{name}")
    fun inspectVolume(
        @Parameter(description = "Volume name or ID")
        @PathVariable name: String
    ): ResponseEntity<DefaultResponse> =
        response { volumeService.inspectVolume(name) }

    @DeleteMapping("{name}/delete")
    fun removeVolume(
        @Parameter(description = "Volume name or ID")
        @PathVariable name: String,
        @Parameter(description = "Force the removal of the volume")
        @RequestParam(required = false, defaultValue = "false") force: Boolean
    ): ResponseEntity<DefaultResponse> =
        response { volumeService.removeVolume(name, force) }

    @DeleteMapping("prune")
    fun deleteUnusedVolumes(
        @Parameter(
            description = "Filters to process on the prune list, encoded as JSON (a map[string][]string)."
        )
        @RequestParam(required = false, defaultValue = "") filters: String
    ): ResponseEntity<DefaultResponse> =
        response { volumeService.deleteUnusedVolumes(filters) }
}
