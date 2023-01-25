package dev.trito.endpoints.server


import dev.trito.endpoints.server.plugins.configureContentNegotiation
import dev.trito.endpoints.server.plugins.configureCors
import dev.trito.endpoints.server.plugins.configureRouting
import io.ktor.server.application.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

/**
 * Sample server app
 */
fun main() {
    embeddedServer(Netty, port = System.getenv("PORT")?.toInt() ?: 8080, host = "0.0.0.0") {
        configurePlugins()
    }.start(wait = true)
}

fun Application.configurePlugins() {
    configureRouting()
    configureCors()
    configureContentNegotiation()
}






