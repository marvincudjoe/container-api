package com.pie.container.api.service

import com.pie.container.api.model.DefaultResponse

interface VolumeService {
    fun listVolumes(filters: String): DefaultResponse
    fun inspectVolume(name: String): DefaultResponse
    fun removeVolume(name: String, force: Boolean): DefaultResponse
    fun deleteUnusedVolumes(filters: String): DefaultResponse
}
