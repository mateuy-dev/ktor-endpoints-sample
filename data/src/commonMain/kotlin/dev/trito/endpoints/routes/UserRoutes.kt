package dev.trito.endpoints.routes

import io.ktor.resources.*
import kotlinx.serialization.Serializable

@Serializable
@Resource("/todoItems")
class TodoItemsRoute {
    @Serializable
    @Resource("new")
    class New(val parent: TodoItemsRoute = TodoItemsRoute())

    @Serializable
    @Resource("{id}")
    class Id(val parent: TodoItemsRoute = TodoItemsRoute(), val id: Long)
}

