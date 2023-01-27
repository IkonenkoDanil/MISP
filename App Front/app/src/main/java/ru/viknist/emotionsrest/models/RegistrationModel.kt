package ru.viknist.emotionsrest.models

import com.google.gson.annotations.SerializedName

data class RegistrationModel(
    @SerializedName("username")
    val username: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("password1")
    val password1: String?,
    @SerializedName("password2")
    val password2: String?,
    @SerializedName("name")
    val name: String?
)
