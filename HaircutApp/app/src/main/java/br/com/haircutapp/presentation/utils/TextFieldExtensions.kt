package br.com.haircutapp.presentation.utils

import br.com.haircutapp.presentation.components.TextField

fun TextField.isValidEmailInput() {
    this.apply {
        onChangeTextInput { email ->
            this.isValidEmailMessage()
        }
    }
}

fun TextField.isValidEmailMessage() {
    this.apply {
        if (this.getTextValue.isValidEmail()) {
            setTextError(null)
        } else {
            setTextError("Digite E-mail valido!")
        }
    }
}

fun TextField.isValidEmptyInput(message: String) {
    this.apply {
        onChangeTextInput { input ->
            this.isValidInputEmptyMessage(message)
        }
    }
}

fun TextField.isValidPasswordInput() {
    this.apply {
        onChangeTextInput { input ->
            this.isValidInputPassword()
        }
    }
}

fun TextField.isValidInputEmptyMessage(message: String) {
    this.apply {
        if (this.getTextValue.isNotEmpty()) {
            setTextError(null)
        } else {
            setTextError(message)
        }
    }
}

fun TextField.isValidInputPassword() {
    this.apply {
        if (this.getTextValue.isNotEmpty() && this.getTextValue?.length == 6) {
            setTextError(null)
        } else {
            setTextError("Digite sua senha com 6 caracteres")
        }
    }
}