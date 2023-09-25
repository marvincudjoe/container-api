package com.pie.container.api.model

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

inline val endpointNotImplemented: ResponseEntity<String>
    get() = ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Not Implemented")

inline fun response(block: () -> DefaultResponse): ResponseEntity<String> {
    val result = block()
    return ResponseEntity.status(result.statusCode).body(result.message)
}

data class DefaultResponse(val statusCode: HttpStatus = HttpStatus.BAD_REQUEST, val message: String = "")
