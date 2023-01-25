package dev.trito.endpoints.server.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

fun Application.configureContentNegotiation() {
    log.debug("Configuring ContentNegotiation")
    install(ContentNegotiation) { json() }
}