package br.com.haircutapp.data

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class FirebaseAuthService(private val auth: FirebaseAuth) {

    fun loggedInUser(): Boolean {
        return auth.currentUser != null
    }

    fun validateEmail(user: FirebaseUser, onSuccess: () -> Unit, onFailure: () -> Unit) {
        user.sendEmailVerification()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess.invoke()
                } else {
                    onFailure.invoke()
                }
            }
    }

    fun isEmailVerified(user: FirebaseUser): Boolean {
        return user.isEmailVerified
    }

    fun loginUser(email: String, password: String, onSuccess: (Task<AuthResult>) -> Unit, onFailure: () -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess.invoke(task)
                } else {
                    onFailure.invoke()
                }
            }
    }

    fun disconnectUser() {
        auth.signOut()
    }

    fun resetPassword(email: String, onSuccess: () -> Unit, onFailure: () -> Unit) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess.invoke()
                } else {
                    onFailure.invoke()
                }
            }

    }

    fun createUser(email: String, password: String, onSuccess: (Task<AuthResult>) -> Unit, onFailure: () -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess.invoke(task)
                } else {
                    onFailure.invoke()
                }
            }
    }

}