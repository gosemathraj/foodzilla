package com.reconnect.reconnectapp.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.gosemathraj.foodzilla.ui.base.BaseActivity

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    @get:LayoutRes
    abstract val layoutResourceId : Int

    protected lateinit var dataBinding: ViewDataBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, layoutResourceId, container, false)
        return dataBinding.root
    }

    /*
    *   Fragment Transactions
    */
    fun replaceFragment(fragment: Fragment, addToBackStack : Boolean, animate : Boolean){
        ((activity) as BaseActivity<*>).replaceFragment(fragment, addToBackStack, animate)
    }
}