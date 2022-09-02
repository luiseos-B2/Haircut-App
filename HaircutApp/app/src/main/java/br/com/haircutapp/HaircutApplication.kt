package br.com.haircutapp

import android.app.Application
import br.com.haircutapp.di.firebaseAuth
import br.com.haircutapp.di.repositoryModule
import br.com.haircutapp.di.serviceModule
import br.com.haircutapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class HaircutApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext ( this@HaircutApplication)
            modules(listOf(firebaseAuth, serviceModule, repositoryModule, viewModelModule))
        }

    }
}