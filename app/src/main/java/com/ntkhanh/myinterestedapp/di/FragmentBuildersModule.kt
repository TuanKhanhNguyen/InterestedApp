package com.ntkhanh.myinterestedapp.di

import androidx.fragment.app.Fragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeRepoFragment(): Fragment
}