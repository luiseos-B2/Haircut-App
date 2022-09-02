package br.com.haircutapp.presentation.forgot_password

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.haircutapp.R
import br.com.haircutapp.presentation.components.TextField
import br.com.haircutapp.presentation.login.viewmodel.LoginViewModel
import br.com.haircutapp.presentation.utils.isNotEmpty
import br.com.haircutapp.presentation.utils.isValidEmail
import br.com.haircutapp.presentation.utils.isValidEmailInput
import br.com.haircutapp.presentation.utils.isValidEmailMessage
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import org.koin.androidx.viewmodel.ext.android.viewModel

class ForgotPasswordActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModel()
    private var inputForgotPassword: TextField? = null
    private var toolbar: MaterialToolbar? = null
    private var parent: ConstraintLayout? = null
    private var progressBar: ProgressBar? = null
    private var forgotPasswordButton: MaterialButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        setupViews()

        forgotPasswordButton?.setOnClickListener {
            sendForgotPassword()
        }

        toolbar?.setNavigationOnClickListener {
            onBackPressed()
        }

        inputForgotPassword?.isValidEmailInput()
    }



    private fun sendForgotPassword() {
        if (inputForgotPassword?.getTextValue.isValidEmail()){
            startProgress(true)
            inputForgotPassword?.getTextValue?.let { email ->
                loginViewModel.resetPassword(
                    email = email,
                    onSuccess = {
                        startProgress(false)
                        Toast.makeText(baseContext, "Foi enviado email para redefinir a senha",
                            Toast.LENGTH_SHORT).show()
                        finish()
                    },
                    onFailure = {
                        startProgress(false)
                        Toast.makeText(baseContext, "Ops... Ocorreu um erro ao enviar email!",
                            Toast.LENGTH_SHORT).show()
                    }
                )
            }
        } else {
            inputForgotPassword?.isValidEmailMessage()
        }
    }

    private fun setupViews() {
        inputForgotPassword = findViewById(R.id.input_forgot_password)
        parent = findViewById(R.id.parent_screen)
        progressBar = findViewById(R.id.progress_forgot_password)
        forgotPasswordButton = findViewById(R.id.forgot_password_button)
        toolbar = findViewById(R.id.mtb_forgot_password)
    }

    private fun startProgress(value: Boolean) {
        if (value) {
            progressBar?.visibility = View.VISIBLE
            parent?.visibility = View.GONE
        } else {
            progressBar?.visibility = View.GONE
            parent?.visibility = View.VISIBLE
        }
    }
}