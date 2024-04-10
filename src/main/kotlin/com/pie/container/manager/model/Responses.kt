package com.pie.container.manager.model

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.net.URI

fun endpointNotImplemented(ref: String): DefaultResponse {
    return (DefaultResponse(HttpStatus.NOT_IMPLEMENTED, URI(ref), (HttpStatus.NOT_IMPLEMENTED.reasonPhrase)))
}

inline fun response(block: () -> DefaultResponse): ResponseEntity<DefaultResponse> {
    val result = block()
    return ResponseEntity.status(result.status).body(result)
}

data class DefaultResponse(
    val status: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR, val reference: URI = URI(""), val body: Any = ""
)
