package br.com.haircutapp.di

import br.com.haircutapp.presentation.login.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel{
        LoginViewModel(loginRepository = get())
    }

}