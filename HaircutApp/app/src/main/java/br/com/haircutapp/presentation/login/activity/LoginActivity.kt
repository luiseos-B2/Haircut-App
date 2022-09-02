package br.com.haircutapp.presentation.login.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.haircutapp.R
import br.com.haircutapp.presentation.components.TextField
import br.com.haircutapp.presentation.forgot_password.ForgotPasswordActivity
import br.com.haircutapp.presentation.home.HomeActivity
import br.com.haircutapp.presentation.login.viewmodel.LoginViewModel
import br.com.haircutapp.presentation.register.RegisterActivity
import br.com.haircutapp.presentation.utils.*
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModel()
    private var inputEmail: TextField? = null
    private var parent: ConstraintLayout? = null
    private var progressBar: ProgressBar? = null
    private var inputPassword: TextField? = null
    private var forgotPassword: MaterialTextView? = null
    private var loginButton: MaterialButton? = null
    private var createAccountButton: MaterialButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupViews()

        loginButton?.setOnClickListener {
            if(loginViewModel.loggedInUser()) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                initLogin()
            }
        }

        createAccountButton?.setOnClickListener {
           val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        forgotPassword?.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        inputEmail?.isValidEmailInput()
        inputPassword?.isValidPasswordInput()

    }

    private fun initLogin() {
        if (inputEmail?.getTextValue.isValidEmail() && inputPassword?.getTextValue.isNotEmpty()){
            startProgress(true)
            inputEmail?.getTextValue?.let { email ->
                inputPassword?.getTextValue?.let { password ->
                    loginViewModel.loginUser(
                        email = email,
                        password = password,
                        onSuccess = { task ->
                            task.result.user?.let { user ->
                                if (loginViewModel.isEmailVerified(user)) {
                                    startProgress(false)
                                    val intent = Intent(this, HomeActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    startProgress(false)
                                    loginViewModel.validateEmail(
                                        user= user,
                                        onSuccess = {
                                            alertValidateEmail()
                                        },
                                        onFailure = {
                                            Toast.makeText(
                                                baseContext, "Não foi possivel enviar Email de confirmação, tente mais tarde!",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    )
                                }
                            }
                        },
                        onFailure = {
                            startProgress(false)
                            Toast.makeText(
                                baseContext, "Email ou senha invalida!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    )
                }
            }
        } else {
            inputEmail?.isValidEmailMessage()
            inputPassword?.isValidInputPassword()
        }
    }

    private fun setupViews() {
        inputEmail = findViewById(R.id.input_email)
        inputPassword = findViewById(R.id.input_password)
        loginButton = findViewById(R.id.login_button)
        createAccountButton = findViewById(R.id.create_account_button)
        parent = findViewById(R.id.parent_screen)
        progressBar = findViewById(R.id.progress_circular)
        forgotPassword = findViewById(R.id.mtv_forgot_password)
    }

    private fun startProgress(value: Boolean) {
            progressBar?.visibility = if (value) View.VISIBLE else View.GONE
            parent?.visibility = if (value) View.GONE else View.VISIBLE
    }

    private fun alertValidateEmail() {
        val dialogBuilder = AlertDialog.Builder(this)

        // set message of alert dialog
        dialogBuilder.setMessage("Foi enviado email de confirmação para seu email, por favor valide antes de fazer login!")
            .setPositiveButton("Ok", DialogInterface.OnClickListener {
                    dialog, id -> dialog.dismiss()
            })
        val alert = dialogBuilder.create()
        alert.setTitle("Valide seu E-mail de cadastro")
        alert.show()
    }

}