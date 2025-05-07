package com.ronivaldoroner.movies.presentation

import android.app.Application
import com.ronivaldoroner.movies.data.di.dataModules
import com.ronivaldoroner.movies.presentation.di.presentationModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MoviesApplication)
            modules(
                presentationModules.plus(dataModules)
            )
        }
    }
}