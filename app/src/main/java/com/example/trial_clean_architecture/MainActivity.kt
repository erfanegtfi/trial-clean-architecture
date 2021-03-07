package com.example.trial_clean_architecture

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.example.trial_clean_architecture.base.BaseViewModel
import com.example.trial_clean_architecture.base.view.BaseActivity
import com.example.trial_clean_architecture.ui.transaction.di.DaggerTransactionComponent
import com.example.trial_clean_architecture.utils.extentions.findAppComponent
import com.example.trial_clean_architecture.utils.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MainActivity : BaseActivity<ViewDataBinding, BaseViewModel>() {

    override val layoutRes: Int = R.layout.activity_main
    override val viewModelClass: Class<BaseViewModel> = BaseViewModel::class.java
    override val bindingVariable: Int? = null

    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerTransactionComponent.factory().create(findAppComponent()).inject(this)
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)

        setupBottomNavigationBar()

    }


    /**
     * Called on first creation and when restoring state.
     */
    private fun setupBottomNavigationBar() {

        val navGraphIds = listOf(R.navigation.contact_nav, R.navigation.transaction_nav, R.navigation.setting_nav)

        // Setup the bottom navigation view with a list of navigation graphs
        val controller = bottom_nav.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_container,
            intent = intent
        )

        // Whenever the selected controller changes, setup the action bar.
        controller.observe(this, Observer { navController ->
//            setupActionBarWithNavController(navController)
        })
        currentNavController = controller
    }


    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }
}