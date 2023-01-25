package dev.trito.ktor.endpoints

import dev.trito.endpoints.client.util.asServiceResult
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.resources.*
import io.ktor.client.request.*
import io.ktor.http.*

/**
 * Call the endpoint with the given input and returns the output as a ServiceResult
 */
suspend inline fun <reified Route : Any, reified Input, reified Output>
        HttpClient.callEndpointForServiceResult(endPoint: EndPoint<Route, Input, Output>, route : Route, input : Input)
        = asServiceResult<Output>{
    callEndpoint(endPoint, route, input)
}

/**
 * Call the endpoint that requires no input and returns the output as a ServiceResult
 */
suspend inline fun <reified Route : Any, reified Output>
        HttpClient.callEndpointForServiceResult(endPoint: EndPoint<Route, Unit, Output>, route : Route)
        = asServiceResult<Output>{
    callEndpoint(endPoint, route, Unit)
}


/**
 * Call the endpoint with the given input and returns the output.
 * Will throw exception in case of response failure
 */
suspend inline fun <reified Route : Any, reified Input, reified Output>
        HttpClient.callEndpointForResult(endPoint: EndPoint<Route, Input, Output>, route : Route, input : Input)
        : Output =
    callEndpoint(endPoint, route, input).body()

/**
 * Calls the endpoint with the given input and returns the response
 */
suspend inline fun <reified Route : Any, reified Input, reified Output>
        HttpClient.callEndpoint(endPoint: EndPoint<Route, Input, Output>, route : Route, input : Input) =
    request(route){
        method = endPoint.httpMethod
        contentType(ContentType.Application.Json)
        setBody(input)
    }