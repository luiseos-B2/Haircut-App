package br.com.haircutapp.presentation.login.viewmodel

import androidx.lifecycle.ViewModel
import br.com.haircutapp.domain.LoginRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

class LoginViewModel(private val loginRepository: LoginRepository): ViewModel() {

    fun createUser(email: String, password: String, onSuccess: (Task<AuthResult>) -> Unit, onFailure: () -> Unit) {
        loginRepository.createUser(email, password, onSuccess, onFailure)
    }

    fun loginUser(email: String, password: String, onSuccess: (Task<AuthResult>) -> Unit, onFailure: () -> Unit) {
        loginRepository.loginUser(email, password, onSuccess, onFailure)
    }

    fun loggedInUser(): Boolean {
        return loginRepository.loggedInUser()
    }

    fun disconnectUser() {
        loginRepository.disconnectUser()
    }

    fun validateEmail(user: FirebaseUser, onSuccess: () -> Unit, onFailure: () -> Unit) {
        loginRepository.validateEmail(user, onSuccess, onFailure)
    }

    fun isEmailVerified(user: FirebaseUser): Boolean {
        return loginRepository.isEmailVerified(user)
    }


    fun resetPassword(email: String, onSuccess: () -> Unit, onFailure: () -> Unit) {
        loginRepository.resetPassword(email, onSuccess, onFailure)
    }
}