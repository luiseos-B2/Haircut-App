package br.com.haircutapp.di


import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module

val firebaseAuth = module {

    single {
        FirebaseAuth.getInstance()
    }

}