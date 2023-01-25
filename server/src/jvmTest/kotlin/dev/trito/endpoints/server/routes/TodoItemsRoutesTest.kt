package dev.trito.endpoints.server.routes

import dev.trito.endpoints.client.*
import dev.trito.endpoints.client.util.ServiceFailure
import dev.trito.endpoints.client.util.ServiceResult
import dev.trito.endpoints.model.TodoItem
import dev.trito.endpoints.model.TodoItemDataImp
import dev.trito.endpoints.server.test.createConfiguredClient
import dev.trito.endpoints.server.test.testClientServerApplication
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.Test
import java.time.Clock


import kotlin.random.Random
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.seconds


internal class TodoItemsRoutesTest{


    @Test
    fun `create adds an item that can be listed and viewed`() = todoItemsRoutesTest { service ->
        val todoItem = randomTodoItem()
        val result = service.create(todoItem)

        assertTrue(result is ServiceResult.Success)
        assertEquals(todoItem.title, result.data.title)

        val listResult = service.list()
        assertTrue(listResult is ServiceResult.Success)
        assertTrue(listResult.data.any { it.title == todoItem.title })

        val viewResult = service.view(result.data.id)
        assertTrue(viewResult is ServiceResult.Success)
        assertEquals(viewResult.data, result.data)

    }

    private fun randomTodoItem() = TodoItemDataImp("TodoItem created at ${System.currentTimeMillis()}")



    fun todoItemsRoutesTest(
        block: suspend ApplicationTestBuilder.(service: TodoItemsService) -> Unit
    ){
        testClientServerApplication{client ->
            val service = TodoItemsService(client)
            block(service)
        }
    }

}