package com.example.trial_clean_architecture.base.view

interface NavigationFragment {

    fun onBackPressed(): Boolean


    fun popToRoot()
}