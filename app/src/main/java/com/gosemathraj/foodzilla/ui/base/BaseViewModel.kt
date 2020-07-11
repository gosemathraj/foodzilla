package com.reconnect.reconnectapp.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException
import com.gosemathraj.foodzilla.data.remote.api.config.Error
import com.gosemathraj.foodzilla.data.remote.api.config.Error.ErrorType
import retrofit2.HttpException

abstract class BaseViewModel : ViewModel() {

    fun launchOnViewModelScope(block: suspend () -> Unit) {
        viewModelScope.launch {
            block()
        }
    }

    class ApiCall<T> {
        lateinit var onSuccess: (data: T?) -> Unit
        lateinit var onEnqueue: suspend () -> T?
        lateinit var onError: (error: Error) -> Unit
    }

    suspend fun <X> apiCall(call: ApiCall<X>.() -> Unit) {
    val apiCallFunction = ApiCall<X>()
        try {
            call(apiCallFunction)
            val response = apiCallFunction.onEnqueue()
            if(response != null) {
                apiCallFunction.onSuccess(response)
            } else {
                apiCallFunction.onError(Error(ErrorType.GENERAL_ERROR, "Something Went Wrong"))
            }
        } catch (ex : Exception) {
            Timber.d(ex)
            when(ex) {
                is IOException -> { apiCallFunction.onError(Error(ErrorType.NETWORK_ERROR, "Network Error")) }
                is HttpException -> { apiCallFunction.onError(Error(ErrorType.GENERAL_ERROR,"Something Went Wrong")) }
                else -> { apiCallFunction.onError(Error(ErrorType.GENERAL_ERROR,"Something Went Wrong")) }
            }
        }
    }
}

