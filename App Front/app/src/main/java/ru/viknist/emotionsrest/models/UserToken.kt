package ru.viknist.emotionsrest.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserToken(
    @SerializedName("access_token") val accessToken: String?,
    @SerializedName("refresh_token") val refreshToken: String?,
    @SerializedName("user") var user: UserData?
) : Serializable
