package dev.trito.endpoints.client


import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.resources.*
import io.ktor.client.plugins.websocket.*
import io.ktor.serialization.kotlinx.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

fun createClient(port : Int = 8080) = HttpClient {
    configureClient(port)
}

fun HttpClientConfig<*>.configureClient(port : Int = 8080){
    install(DefaultRequest)
    defaultRequest {
        this.port = port
    }
    install(Logging)
    install(ContentNegotiation) {
        json(Json)
    }
    install(WebSockets) {
        contentConverter = KotlinxWebsocketSerializationConverter(Json)
    }
    install(Resources)
}