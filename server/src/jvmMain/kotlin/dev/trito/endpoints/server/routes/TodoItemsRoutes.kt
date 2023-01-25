package dev.trito.endpoints.server.routes


import dev.trito.endpoints.routes.EndPoints
import dev.trito.endpoints.server.repositories.TodoItemsRepository
import dev.trito.ktor.endpoints.respondEndpoint

import io.ktor.server.application.*

import io.ktor.server.routing.*

val todoItemsRepository = TodoItemsRepository()

fun Application.todosRoutes() {
    routing {
        respondEndpoint(EndPoints.TodoItems.List){ _, _ ->
            todoItemsRepository.list()
        }
        respondEndpoint(EndPoints.TodoItems.View){resource, _ ->
            todoItemsRepository.find(resource.id)
        }
        respondEndpoint(EndPoints.TodoItems.Create) { _, data ->
            todoItemsRepository.addItem(data)
        }
    }
}

