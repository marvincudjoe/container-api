package com.pie.container.manager.service

import com.pie.container.manager.model.DefaultResponse

interface VolumeService {
    fun listVolumes(filters: String): DefaultResponse
    fun inspectVolume(name: String): DefaultResponse
    fun removeVolume(name: String, force: Boolean): DefaultResponse
    fun deleteUnusedVolumes(filters: String): DefaultResponse
}
