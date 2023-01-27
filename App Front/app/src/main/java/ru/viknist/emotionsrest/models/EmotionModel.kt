package ru.viknist.emotionsrest.models

import com.google.gson.annotations.SerializedName

data class EmotionModel(
    @SerializedName("text") val text: String?,
    @SerializedName("emotion") val emotion: String?
)
