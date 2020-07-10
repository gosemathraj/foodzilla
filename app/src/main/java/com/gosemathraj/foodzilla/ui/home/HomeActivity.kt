package com.gosemathraj.foodzilla.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.gosemathraj.foodzilla.R
import com.gosemathraj.foodzilla.data.models.Food
import com.gosemathraj.foodzilla.databinding.ActivityHomeBinding
import com.gosemathraj.foodzilla.ui.base.BaseActivity
import com.gosemathraj.foodzilla.ui.home.adapter.FoodListAdapter
import com.reconnect.reconnectapp.data.remote.api.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    private val homeViewModel : HomeViewModel by viewModels()
    private var foodList = ArrayList<Food>()
    private var filteredFoodList = ArrayList<Food>()
    private val foodAdapter = FoodListAdapter(foodList) { foodItem -> onFoodItemClicked(foodItem) }

    override val layoutResourceId: Int
        get() = R.layout.activity_home

    override val currentFragment: Fragment?
        get() = null

    override val fragmentContainerId: Int
        get() = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        setListeners()
        setRecyclerview()
        setObservers()
    }

    private fun setListeners() {
        edt_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filteredFoodList.clear()
                if (s.toString().isNotEmpty()) {
                    filteredFoodList.addAll(foodList.filter {
                        it.title?.toLowerCase()?.contains(s.toString().toLowerCase())!!
                    } as ArrayList<Food>)
                } else {
                    filteredFoodList.addAll(foodList)
                }
                foodAdapter.refreshList(filteredFoodList)
                foodAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun setRecyclerview() {
        rv_foodlist.apply {
            this.setHasFixedSize(true)
            adapter = foodAdapter
        }
    }

    private fun onFoodItemClicked(foodItem : Food) {
        showSnack(foodItem.getTagList())
    }

    private fun setObservers() {
        homeViewModel.foodListLiveData.observe(this, Observer {
            when(it.status) {
                Resource.Status.LOADING -> {
                    initLoader()
                }
                Resource.Status.SUCCESS -> {
                    finishLoader()
                    it.data?.let { foodList ->
                        this.foodList = foodList as ArrayList<Food>
                        foodAdapter.refreshList(foodList)
                        foodAdapter.notifyDataSetChanged()
                    }
                }
                Resource.Status.ERROR -> {
                    finishLoader()
                    it.error?.let { error ->
                        error.message?.let { message -> showToast(message) }
                    }
                }
            }
        })
    }
}