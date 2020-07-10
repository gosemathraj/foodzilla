package com.gosemathraj.foodzilla.ui.home

import androidx.fragment.app.Fragment
import com.gosemathraj.foodzilla.R
import com.gosemathraj.foodzilla.databinding.ActivityHomeBinding
import com.gosemathraj.foodzilla.ui.base.BaseActivity

class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_home

    override val currentFragment: Fragment?
        get() = null

    override val fragmentContainerId: Int
        get() = 0
}