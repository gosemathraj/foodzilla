package com.gosemathraj.foodzilla.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.gosemathraj.foodzilla.R
import com.gosemathraj.foodzilla.data.remote.models.Food
import com.gosemathraj.foodzilla.databinding.ActivityHomeBinding
import com.gosemathraj.foodzilla.ui.base.BaseActivity
import com.gosemathraj.foodzilla.ui.home.adapter.FoodListAdapter
import com.reconnect.reconnectapp.data.remote.api.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    private val homeViewModel : HomeViewModel by viewModels()
    private val foodList = ArrayList<Food>()
    private val foodAdapter = FoodListAdapter(foodList)

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
        setRecyclerview()
        setObservers()
    }

    private fun setRecyclerview() {
        rv_foodlist.apply {
            this.setHasFixedSize(true)
            adapter = foodAdapter
        }
    }

    private fun setObservers() {
        homeViewModel.foodListLiveData.observe(this, Observer {
            when(it) {
                Resource.Status.LOADING -> {
                    initLoader()
                }
                Resource.Status.SUCCESS -> {
                    it.data?.let { foodList ->
                        foodAdapter.refreshList(foodList)
                        foodAdapter.notifyDataSetChanged()
                    }
                }
                Resource.Status.ERROR -> {
                    it.error?.let { error ->
                        error.message?.let { message -> showToast(message) }
                    }
                }
            }
        })
    }
}