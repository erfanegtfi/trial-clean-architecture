package com.example.trial_clean_architecture.utils.extentions

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import com.example.trial_clean_architecture.di.component.AppComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@SuppressLint("NewApi")
fun Fragment.findAppComponent(): AppComponent {
   return requireActivity().findAppComponent()
}
