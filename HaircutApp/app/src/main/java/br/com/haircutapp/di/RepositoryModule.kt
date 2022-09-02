package br.com.haircutapp.di

import br.com.haircutapp.domain.LoginRepository
import org.koin.dsl.module

val repositoryModule = module {

    single {
        LoginRepository(firebaseAuthService = get())
    }

}