package dev.trito.endpoints.model


import kotlinx.serialization.Serializable


@Serializable
data class TodoItem(
    override val id: Long,
    override val title: String,
    override val done: Boolean
): IdModel, TodoItemData

interface TodoItemData{
    val title: String
    val done: Boolean
}

@Serializable
data class TodoItemDataImp(override val title: String, override val done: Boolean = false) : TodoItemData


