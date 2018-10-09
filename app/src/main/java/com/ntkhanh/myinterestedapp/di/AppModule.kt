package com.ntkhanh.myinterestedapp.di

import com.ntkhanh.myinterestedapp.InterestedApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    fun provideContext(interestedApp: InterestedApp) : InterestedApp{
        return interestedApp
    }
}