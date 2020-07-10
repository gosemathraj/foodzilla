package com.gosemathraj.foodzilla.ui.base

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.gosemathraj.foodzilla.R
import com.gosemathraj.foodzilla.utils.safeExecute

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {

    @get:LayoutRes
    abstract val layoutResourceId : Int

    abstract val currentFragment : Fragment?

    abstract val fragmentContainerId : Int

    protected lateinit var dataBinding : T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, layoutResourceId)
    }

    /*
    *   Fragment Transactions
    */
    fun addFragment(fragment : Fragment) {
        addFragment(fragment, addToBackStack = false, animate = false)
    }

    fun addFragment(fragment : Fragment, addToBackStack : Boolean, animate : Boolean) {
        safeExecute {
            if (fragment == null || fragment.isAdded || currentFragment?.javaClass?.name == fragment.javaClass.name) {
                return@safeExecute
            }
            val transaction = supportFragmentManager.beginTransaction()
            if (!isFinishing) {
                if (animate) {
                    transaction.setCustomAnimations(
                        R.anim.enter_from_right,
                        R.anim.exit_to_left,
                        R.anim.enter_from_left,
                        R.anim.exit_to_right
                    )
                }
                if (addToBackStack) {
                    transaction.addToBackStack(fragment.javaClass.simpleName)
                }
                transaction.add(fragmentContainerId, fragment, fragment.javaClass.simpleName)
                transaction.commit()
            }
        }
    }

    fun replaceFragment(fragment : Fragment, addToBackStack : Boolean, animate : Boolean) {
        safeExecute {
            if (fragment == null || fragment.isAdded || currentFragment?.javaClass?.name == fragment.javaClass.name) {
                return@safeExecute
            }
            val transaction = supportFragmentManager.beginTransaction()
            if (!isFinishing) {
                if (animate) {
                    transaction.setCustomAnimations(
                        R.anim.enter_from_right,
                        R.anim.exit_to_left,
                        R.anim.enter_from_left,
                        R.anim.exit_to_right
                    )
                }
                if (addToBackStack) {
                    transaction.addToBackStack(fragment.javaClass.simpleName)
                }
                transaction.replace(fragmentContainerId, fragment, fragment.javaClass.simpleName)
                transaction.commit()
            }
        }
    }

    /**
     *  Progressbar Dialog
     */
    private val progressBar: AlertDialog? by lazy {
        this.let {
            AlertDialog.Builder(it, R.style.ProgressDialog).setView(R.layout.layout_loading)
                .setCancelable(false).create()
        }
    }

    fun initLoader() {
        progressBar?.let { myProgressBar ->
            if (!myProgressBar.isShowing) {
                myProgressBar.show()
            } else {
                if (this.isFinishing) {
                    myProgressBar.show()
                }
            }
        }
    }

    fun finishLoader() {
        try {
            progressBar?.let {
                if (it.isShowing) {
                    it.dismiss()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     *  Show Toast
     */
    fun showToast(toastMessage : String) {
        Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show()
    }

    fun showToast(toastMessage: String, length : Int) {
        Toast.makeText(this, toastMessage, length).show()
    }

    /**
     *  Show Snackbar
     */
    fun showSnack(snackMessage : String) {
        Snackbar.make(findViewById(android.R.id.content), snackMessage, Snackbar.LENGTH_SHORT).show()
    }

    /**
     *  Activity Navigator
     */
    fun startActivity(c: Class<*>) {
        val intent = Intent(this, c)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        finishLoader()
    }
}