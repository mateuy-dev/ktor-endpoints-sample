package dev.trito.endpoints.server.plugins



import dev.trito.endpoints.server.routes.todosRoutes
import io.ktor.server.application.*
import io.ktor.server.resources.*


fun Application.configureRouting() {
    log.debug("Configuring routes")
    install(Resources)
    todosRoutes()
}


