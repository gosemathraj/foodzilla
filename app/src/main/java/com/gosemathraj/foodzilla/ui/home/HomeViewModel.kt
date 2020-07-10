package com.gosemathraj.foodzilla.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.gosemathraj.foodzilla.data.remote.dto.FoodDTO
import com.gosemathraj.foodzilla.data.remote.models.Food
import com.gosemathraj.foodzilla.data.repo.FoodRepository
import com.reconnect.reconnectapp.data.remote.api.Resource
import com.reconnect.reconnectapp.ui.base.BaseViewModel

class HomeViewModel @ViewModelInject constructor(
    private val foodRepository: FoodRepository) : BaseViewModel() {

    var foodListLiveData = MutableLiveData<Resource<List<Food>>>()
    var foodList = ArrayList<Food>()

    init {
        getFoodList()
    }

    private fun getFoodList() {
        launchOnViewModelScope {
            foodListLiveData.value = Resource.loading()
            apiCall<List<FoodDTO>> {
                onEnqueue = {
                    foodRepository.getFoodList()
                }
                onSuccess = {
                    it?.let { foodDtoList ->
                        foodDtoList.forEach {
                            val food = Food()
                            food.image = it.image
                            food.filter = it.filter
                            food.title = it.title
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
    }
}