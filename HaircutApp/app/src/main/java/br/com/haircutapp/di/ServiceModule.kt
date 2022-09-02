package br.com.haircutapp.di

import br.com.haircutapp.data.FirebaseAuthService
import org.koin.dsl.module

val serviceModule = module {

    single {
        FirebaseAuthService(auth = get())
    }

}