package dev.trito.endpoints.server.repositories

import dev.trito.endpoints.model.TodoItem
import dev.trito.endpoints.model.TodoItemData
import java.util.concurrent.atomic.AtomicLong

val idManager = AtomicLong()
class TodoItemsRepository(val todoItems : MutableList<TodoItem> = mutableListOf()) {

    fun list() : List<TodoItem> = todoItems

    fun addItem(data : TodoItemData) : TodoItem{
        val id = newId()
        todoItems+=TodoItem(id, data.title, data.done)
        return find(id)
    }

    fun find(id : Long) = todoItems.find { it.id == id }!!

    private fun newId(): Long  = idManager.incrementAndGet()
}