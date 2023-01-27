package ru.viknist.emotionsrest.models

import com.google.gson.annotations.SerializedName

data class PatchUserData(
    @SerializedName("email") var email: String?,
    @SerializedName("name") var name: String?
)
