package dev.trito.endpoints.client

import dev.trito.ktor.endpoints.callEndpointForServiceResult
import dev.trito.endpoints.model.TodoItemDataImp
import dev.trito.endpoints.routes.EndPoints
import dev.trito.endpoints.routes.TodoItemsRoute
import io.ktor.client.*

class TodoItemsService(private val client : HttpClient = createClient())  {
    suspend fun list() = client.callEndpointForServiceResult(EndPoints.TodoItems.List, TodoItemsRoute())

    suspend fun create(todoItemData: TodoItemDataImp) = client.callEndpointForServiceResult(EndPoints.TodoItems.Create, TodoItemsRoute.New(), todoItemData)

    suspend fun view(id: Long) = client.callEndpointForServiceResult(EndPoints.TodoItems.View, TodoItemsRoute.Id(id = id), "")
}