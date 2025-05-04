package com.ronivaldoroner.movies.presentation

import android.app.Application
import com.ronivaldoroner.movies.data.di.dataModule
import com.ronivaldoroner.movies.local.di.localModule
import com.ronivaldoroner.movies.presentation.di.presentationModules
import com.ronivaldoroner.movies.remote.di.remoteModule
import org.koin.core.context.GlobalContext.startKoin

class MoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                presentationModules
                    .plus(dataModule)
                    .plus(remoteModule)
                    .plus(localModule)
            )
        }
    }

}