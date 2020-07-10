package com.reconnect.reconnectapp.data.remote.api

import timber.log.Timber
import java.io.IOException

class FoodzillaApiClient<T> {

    lateinit var onSuccess: (data : List<T>?, successMessage : String?) -> Unit
    lateinit var onEnqueue: suspend () -> List<T>
    lateinit var onError: (error : Error) -> Unit

    suspend fun <X> apiCall(call: FoodzillaApiClient<X>.() -> Unit) {
        val apiCallFunction = FoodzillaApiClient<X>()
        try {
            call(apiCallFunction)
            val response = apiCallFunction.onEnqueue()
            if(response != null) {
                apiCallFunction.onSuccess(response, "")
            } else {
                apiCallFunction.onError(Error("Something Went Wrong"))
            }
        } catch (ex : Exception) {
            Timber.d(ex)
            when(ex) {
                is IOException -> { apiCallFunction.onError(Error("Network Error")) }
                else -> { apiCallFunction.onError(Error("Something Went Wrong")) }
            }
        }
    }
}