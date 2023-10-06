package com.pie.container.api.service

import com.pie.container.api.model.DefaultResponse

interface SystemService {
    fun pingDaemon()
    fun version(): DefaultResponse
}
