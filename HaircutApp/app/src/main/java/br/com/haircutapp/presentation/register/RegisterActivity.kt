package br.com.haircutapp.presentation.register

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.haircutapp.R
import br.com.haircutapp.presentation.components.TextField
import br.com.haircutapp.presentation.login.viewmodel.LoginViewModel
import br.com.haircutapp.presentation.utils.*
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.text.isNotEmpty

class RegisterActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModel()
    private var inputPassword: TextField? = null
    private var inputPasswordRepeat: TextField? = null
    private var inputEmail: TextField? = null
    private var toolbar: MaterialToolbar? = null
    private var parent: ConstraintLayout? = null
    private var progressBar: ProgressBar? = null
    private var createAccountButton: MaterialButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setupViews()
        createAccountButton?.setOnClickListener {
            createAccount()
        }

        toolbar?.setNavigationOnClickListener {
            onBackPressed()
        }

        inputEmail?.isValidEmailInput()
        inputPassword?.isValidPasswordInput()
        repeatPasswordRule()
    }

    private fun repeatPasswordRule() {
        inputPasswordRepeat?.onChangeTextInput {
            validRepeatPassword()
        }
    }

    private fun validRepeatPassword() {
        inputPasswordRepeat?.apply {
            getTextValue?.let { repeatPassword ->
                inputPassword?.getTextValue?.let { password ->
                    if (password == repeatPassword && repeatPassword.isNotEmpty()) {
                        setTextError(null)
                    } else {
                        setTextError("Repita a senha")
                    }
                }
            }
        }
    }

    private fun createAccount() {
        if (inputEmail?.getTextValue.isValidEmail() && inputPassword?.getTextValue.isNotEmpty() && inputPassword?.getTextValue == inputPasswordRepeat?.getTextValue) {
            startProgress(true)
            inputEmail?.getTextValue?.let { email ->
                inputPassword?.getTextValue?.let { password ->
                    loginViewModel.createUser(
                        email = email,
                        password = password,
                        onSuccess = { task ->
                            task.result.user?.let { user ->
                                loginViewModel.validateEmail(
                                    user = user,
                                    onFailure = {
                                        startProgress(false)
                                        Toast.makeText(
                                            baseContext, "Ops... Ocorreu um erro ao criar a conta!",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    },
                                    onSuccess =  {
                                        startProgress(false)
                                        alertValidateEmail()
                                    }
                                )
                            }
                        },
                        onFailure = {
                            startProgress(false)
                            Toast.makeText(
                                baseContext, "Ops... Ocorreu um erro ao criar a conta!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    )
                }
            }
        } else {
            inputEmail?.isValidEmailMessage()
            inputPassword?.isValidInputPassword()
            validRepeatPassword()
        }
    }

    private fun alertValidateEmail() {
        val dialogBuilder = AlertDialog.Builder(this)

        // set message of alert dialog
        dialogBuilder.setMessage("Foi enviado email de confirmação para seu email, por favor valide!")
            // positive button text and action
            .setPositiveButton("Ok", DialogInterface.OnClickListener {
                    dialog, id -> finish()
            })


        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle("Valide seu E-mail de cadastro")
        // show alert dialog
        alert.show()
    }

    private fun setupViews() {
        inputEmail = findViewById(R.id.input_email)
        inputPassword = findViewById(R.id.input_password)
        inputPasswordRepeat = findViewById(R.id.input_repeat_password)
        parent = findViewById(R.id.parent_screen)
        progressBar = findViewById(R.id.progress_register)
        createAccountButton = findViewById(R.id.create_account_button)
        createAccountButton = findViewById(R.id.create_account_button)
        toolbar = findViewById(R.id.mtb_register)
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