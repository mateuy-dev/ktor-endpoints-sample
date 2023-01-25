package dev.trito.endpoints.client.util

import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.utils.io.errors.*

/**
 * calls the given request and processes the result as a ServiceResult
 */
suspend inline fun <reified T> asServiceResult(makeRequest : ()-> HttpResponse) : ServiceResult<T> {
    return try {
        val response = makeRequest()
        if(response.status.value in 200..299){
            ServiceResult.Success(response.body())
        } else {
            val failure = when(response.status){
                HttpStatusCode.Unauthorized -> ServiceFailure.AUTHENTICATION
                else -> ServiceFailure.UNKNOWN
            }
            ServiceResult.Failure(failure)
        }
    } catch (e : IOException){
        ServiceResult.Failure(ServiceFailure.NO_CONNECTION)
    }
}