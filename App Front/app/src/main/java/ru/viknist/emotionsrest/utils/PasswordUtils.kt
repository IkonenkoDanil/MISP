package ru.viknist.emotionsrest.utils

import android.text.InputType
import android.widget.EditText

object PasswordUtils {

    fun showHidePassword(password: EditText) {
        if (password.inputType == 129 or 1 or 0) {
            password.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            password.setSelection(password.text.length)
        } else if (password.inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
            password.inputType = 129
            password.setSelection(password.text.length)
        }
    }
}