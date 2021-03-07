package com.example.trial_clean_architecture.utils.extentions

import android.annotation.SuppressLint
import android.app.Activity
import com.example.trial_clean_architecture.App
import com.example.trial_clean_architecture.di.component.AppComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@SuppressLint("NewApi")
fun Activity.findAppComponent(): AppComponent {
   return (application as App).getAppComponent()
}
