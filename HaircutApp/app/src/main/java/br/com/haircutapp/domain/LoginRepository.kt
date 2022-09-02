package br.com.haircutapp.domain

import br.com.haircutapp.data.FirebaseAuthService
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

class LoginRepository(private val firebaseAuthService: FirebaseAuthService) {

    fun createUser(email: String, password: String, onSuccess: (Task<AuthResult>) -> Unit, onFailure: () -> Unit) {
        firebaseAuthService.createUser(email, password, onSuccess, onFailure)
    }

    fun loginUser(email: String, password: String, onSuccess: (Task<AuthResult>) -> Unit, onFailure: () -> Unit) {
        firebaseAuthService.loginUser(email, password, onSuccess, onFailure)
    }

    fun loggedInUser(): Boolean {
        return firebaseAuthService.loggedInUser()
    }

    fun validateEmail(user: FirebaseUser, onSuccess: () -> Unit, onFailure: () -> Unit) {
        firebaseAuthService.validateEmail(user, onSuccess, onFailure)
    }

    fun isEmailVerified(user: FirebaseUser): Boolean {
        return firebaseAuthService.isEmailVerified(user)
    }

    fun disconnectUser() {
        firebaseAuthService.disconnectUser()
    }

    fun resetPassword(email: String, onSuccess: () -> Unit, onFailure: () -> Unit) {
        firebaseAuthService.resetPassword(email, onSuccess, onFailure)
    }

}