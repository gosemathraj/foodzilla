package com.gosemathraj.foodzilla.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.gosemathraj.foodzilla.R
import com.gosemathraj.foodzilla.data.models.Food
import com.gosemathraj.foodzilla.data.remote.api.config.Error
import com.gosemathraj.foodzilla.data.remote.api.config.Resource
import com.gosemathraj.foodzilla.databinding.ActivityHomeBinding
import com.gosemathraj.foodzilla.ui.base.BaseActivity
import com.gosemathraj.foodzilla.ui.home.adapter.FoodListAdapter
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
                tv_error.visibility = View.GONE
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

                if (filteredFoodList.isNullOrEmpty()) {
                    tv_error.visibility = View.VISIBLE
                    tv_error.text = "No Data Found"
                }
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
                    if (!it.data.isNullOrEmpty()) {
                        this.foodList = it.data as ArrayList<Food>
                        foodAdapter.refreshList(foodList)
                        foodAdapter.notifyDataSetChanged()
                    } else {
                        tv_error.visibility = View.VISIBLE
                        tv_error.text = "No Data Found"
                    }
                }
                Resource.Status.ERROR -> {
                    finishLoader()
                    it.error?.let { error ->
                        if (error.errorType == Error.ErrorType.NO_INTERNET_ERROR) {
                            tv_error.visibility = View.VISIBLE
                            tv_error.text = "No Internet Connection"
                        } else {
                            it.error.errorMessage?.let { errorMsg -> showToast(errorMsg) }
                        }
                    }
                }
            }
        })
    }
}