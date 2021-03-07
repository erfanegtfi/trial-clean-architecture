package com.example.trial_clean_architecture

import android.app.Application
import com.example.trial_clean_architecture.di.component.AppComponent
import com.example.trial_clean_architecture.di.component.DaggerAppComponent
import com.snakydesign.watchtower.WatchTower
import com.snakydesign.watchtower.interceptor.WebWatchTowerObserver

class App : Application() {

    lateinit var component: AppComponent


    override fun onCreate() {
        super.onCreate()

        createComponent()
        component.inject(this)
        WatchTower.start(WebWatchTowerObserver(port = 8085))
    }

    private fun createComponent() {
        component = DaggerAppComponent.factory().create(this);
    }

    fun getAppComponent(): AppComponent {
        return component
    }
}