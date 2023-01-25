package dev.trito.endpoints.server.test


import dev.trito.endpoints.server.configurePlugins
import io.ktor.client.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.resources.*
import io.ktor.client.plugins.websocket.*
import io.ktor.serialization.kotlinx.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import io.ktor.server.testing.*


fun testClientServerApplication(
        block: suspend ApplicationTestBuilder.(client: HttpClient) -> Unit
    ) {
        testApplication{
            val customClient = createConfiguredClient()
            application {
                configurePlugins()
            }
            block(customClient)
        }
    }

fun ApplicationTestBuilder.createConfiguredClient(): HttpClient = createClient{
        install(Logging)
        install(ContentNegotiation) {
            json(Json)
        }
        install(WebSockets){
            contentConverter = KotlinxWebsocketSerializationConverter(Json)
        }
        install(Resources)
    }

