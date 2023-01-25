package dev.trito.endpoints

import dev.trito.endpoints.client.util.ServiceResult
import dev.trito.endpoints.client.TodoItemsService
import dev.trito.endpoints.model.TodoItemDataImp
import kotlinx.coroutines.runBlocking

val todoItemsService = TodoItemsService()
fun main() = runBlocking {

    createItem()
    createItem()
    createItem()
    createItem()


    listItems()

}

suspend fun createItem() {
    val result = todoItemsService.create(TodoItemDataImp("Item created at ${System.currentTimeMillis()}"))
    if(result is ServiceResult.Success){
        println("created: ${result.data}")
    }
}

suspend fun listItems() {
    val result = todoItemsService.list()
    if(result is ServiceResult.Success){
        result.data.forEach(::println)
    }
}