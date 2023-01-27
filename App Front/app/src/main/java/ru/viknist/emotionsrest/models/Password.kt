package ru.viknist.emotionsrest.models

import com.google.gson.annotations.SerializedName

data class Password(
    @SerializedName("new_password1")
    val password1: String?,
    @SerializedName("new_password2")
    val password2: String?
)
