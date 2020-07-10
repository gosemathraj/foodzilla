package com.gosemathraj.foodzilla.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import com.gosemathraj.foodzilla.data.repo.FoodRepository
import com.reconnect.reconnectapp.ui.base.BaseViewModel

class HomeViewModel @ViewModelInject constructor(
    private val foodRepository: FoodRepository) : BaseViewModel() {


}