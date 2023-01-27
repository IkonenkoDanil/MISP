package ru.viknist.emotionsrest.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserData(
    @SerializedName("id") val idUser: Int?,
    @SerializedName("username") val username: String?,
    @SerializedName("email") var email: String?,
    @SerializedName("password") val password: String?,
    @SerializedName("name") var name: String?
) : Serializable

