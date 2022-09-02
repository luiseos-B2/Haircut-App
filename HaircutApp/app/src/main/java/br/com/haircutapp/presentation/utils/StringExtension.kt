package br.com.haircutapp.presentation.utils

import android.text.TextUtils

fun String?.isValidEmail(): Boolean {
    return if (TextUtils.isEmpty(this)) {
        false;
    } else {
        android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches();
    }
}

fun String?.isNotEmpty(): Boolean {
    return !TextUtils.isEmpty(this)
}
