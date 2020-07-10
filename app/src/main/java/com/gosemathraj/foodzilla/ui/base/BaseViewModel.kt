package com.reconnect.reconnectapp.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

abstract class BaseViewModel() : ViewModel() {

    fun launchOnViewModelScope(block: suspend () -> Unit) {
        viewModelScope.launch {
            block()
        }
    }
}