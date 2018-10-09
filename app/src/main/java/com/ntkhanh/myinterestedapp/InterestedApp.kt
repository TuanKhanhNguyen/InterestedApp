package com.ntkhanh.myinterestedapp

import android.app.Activity
import android.app.Application
import com.ntkhanh.myinterestedapp.di.AppInjector
import com.ntkhanh.myinterestedapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import timber.log.Timber
import javax.inject.Inject
import dagger.android.HasActivityInjector

class InterestedApp : Application(), HasActivityInjector {


    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
       AppInjector.init(this)
    }

    override fun activityInjector() = dispatchingAndroidInjector
}