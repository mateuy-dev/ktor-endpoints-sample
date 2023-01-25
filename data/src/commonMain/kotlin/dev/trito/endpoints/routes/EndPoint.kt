package dev.trito.endpoints.routes

import dev.trito.endpoints.model.TodoItem
import dev.trito.endpoints.model.TodoItemDataImp
import dev.trito.ktor.endpoints.EndPoint
import dev.trito.ktor.endpoints.defineEndPoint
import io.ktor.http.*



object EndPoints{
    object TodoItems {
        val List = defineEndPoint<TodoItemsRoute, Unit, List<TodoItem>>(HttpMethod.Get)
        val View = defineEndPoint<TodoItemsRoute.Id, String, TodoItem>(HttpMethod.Get)
        val Create = defineEndPoint<TodoItemsRoute.New, TodoItemDataImp, TodoItem>(HttpMethod.Put)
    }
}