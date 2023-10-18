package com.pie.container.api.model

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.net.URI

inline val endpointNotImplemented: ResponseEntity<String>
    get() = ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Not Implemented")

inline fun response(block: () -> DefaultResponse): ResponseEntity<DefaultResponse> {
    val result = block()
    return ResponseEntity.status(result.status).body(result)
}

data class DefaultResponse(
    val status: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
    val reference: URI = URI(""),
    val body: Any = ""
)
