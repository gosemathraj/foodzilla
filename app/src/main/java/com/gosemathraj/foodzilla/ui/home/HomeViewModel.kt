package com.gosemathraj.foodzilla.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.gosemathraj.foodzilla.BuildConfig
import com.gosemathraj.foodzilla.data.remote.dto.FoodDTO
import com.gosemathraj.foodzilla.data.models.Food
import com.gosemathraj.foodzilla.data.remote.api.config.Error
import com.gosemathraj.foodzilla.data.remote.api.config.Resource
import com.gosemathraj.foodzilla.data.repo.FoodRepository
import com.gosemathraj.foodzilla.utils.NetworkUtils
import com.reconnect.reconnectapp.ui.base.BaseViewModel

class HomeViewModel @ViewModelInject constructor(
    private val foodRepository: FoodRepository,
    private val networkUtils: NetworkUtils) : BaseViewModel() {

    var foodListLiveData = MutableLiveData<Resource<List<Food>>>()
    var foodList = ArrayList<Food>()

    init {
        getFoodList()
    }

    private fun getFoodList() {
        if (networkUtils.isNetworkConnected()) {
            launchOnViewModelScope {
                foodListLiveData.value = Resource.loading()
                apiCall<List<FoodDTO>> {
                    onEnqueue = {
                        foodRepository.getFoodList()
                    }
                    onSuccess = {
                        it?.let { foodDtoList ->
                            foodDtoList.forEach { foodDto ->
                                val food = Food()
                                food.image = BuildConfig.BASE_URL +  foodDto.image
                                food.filter = foodDto.filter
                                food.title = foodDto.title
                                foodList.add(food)
                            }
                        }
                        foodListLiveData.value = Resource.success(foodList)
                    }
                    onError = {
                        foodListLiveData.value = Resource.error(it)
                    }
                }
            }
        } else {
            foodListLiveData.value = Resource.error(Error(
                Error.ErrorType.NO_INTERNET_ERROR, ""))
        }
    }
}