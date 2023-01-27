package ru.viknist.emotionsrest.models

import com.google.gson.annotations.SerializedName

data class NoteModel(
    @SerializedName("text") val text: String?,
    @SerializedName("emotion") val emotion: String?,
    @SerializedName("time") val time: String?
)
