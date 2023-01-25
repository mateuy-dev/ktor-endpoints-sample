package dev.trito.ktor.endpoints

import dev.trito.ktor.endpoints.EndPoint
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.resources.*
import io.ktor.server.resources.handle
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*
import kotlinx.serialization.serializer


/**
 * Defines a route for the given endpoint
 */
inline fun <reified Resource : Any, reified Input : Any, reified Output: Any> Route.respondEndpoint(
    endPoint: EndPoint<Resource, Input, Output>,
    noinline body: suspend PipelineContext<Unit, ApplicationCall>.(Resource, Input) -> Output
): Route {
    lateinit var builtRoute: Route
    resource<Resource> {
        builtRoute = method(endPoint.httpMethod) {
            val serializer = serializer<Resource>()
            handle (serializer){ resource ->
                val input = if (endPoint.hasNoInput) Unit as Input else call.receive()
                val result = body(resource, input)
                call.respond(result)
            }
        }
    }
    return builtRoute
}