package com.gosemathraj.foodzilla.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gosemathraj.foodzilla.R
import com.gosemathraj.foodzilla.data.models.Food
import com.gosemathraj.foodzilla.databinding.FoodItemBinding

class FoodListAdapter(private var foodList: List<Food>, val onFoodItemClick : (Food) -> Unit) :
    RecyclerView.Adapter<FoodListAdapter.FoodListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FoodListViewHolder(DataBindingUtil.inflate(inflater, R.layout.food_item, parent, false))
    }

    override fun onBindViewHolder(holder: FoodListViewHolder, position: Int) {
        holder.binding.food = foodList[position]
        holder.binding.executePendingBindings()

        holder.binding.root.setOnClickListener {
            onFoodItemClick(foodList[position])
        }
    }

    override fun getItemCount(): Int = foodList.size

    fun refreshList(foodList : List<Food>) {
        this.foodList = foodList
    }

    class FoodListViewHolder(val binding : FoodItemBinding) : RecyclerView.ViewHolder(binding.root)

}