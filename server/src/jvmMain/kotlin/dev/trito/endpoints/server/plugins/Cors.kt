package dev.trito.endpoints.server.plugins




import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.application.*

import io.ktor.server.http.content.*
import io.ktor.server.plugins.cors.routing.*



fun Application.configureCors() {
    log.debug("Configuring CORS")
    install(CORS) {
        anyHost()
        allowHeader(HttpHeaders.ContentType)
        allowHeader(HttpHeaders.Authorization)
        allowMethod(HttpMethod.Post)
    }
}


