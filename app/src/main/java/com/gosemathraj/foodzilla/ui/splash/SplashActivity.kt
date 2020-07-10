package com.gosemathraj.foodzilla.ui.splash

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import com.gosemathraj.foodzilla.R
import com.gosemathraj.foodzilla.databinding.ActivitySplashBinding
import com.gosemathraj.foodzilla.ui.base.BaseActivity
import com.gosemathraj.foodzilla.ui.home.HomeActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_splash

    override val currentFragment: Fragment?
        get() = null

    override val fragmentContainerId: Int
        get() = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initCounter()
    }

    private fun initCounter() {
        object : CountDownTimer(3000, 1000) {
            override fun onFinish() {
                finish()
                startActivity(HomeActivity::class.java)
            }

            override fun onTick(millisUntilFinished: Long) {}
        }.start()
    }


}