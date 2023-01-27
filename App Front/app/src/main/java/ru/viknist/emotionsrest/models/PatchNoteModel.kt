package ru.viknist.emotionsrest.models

import com.google.gson.annotations.SerializedName

data class PatchNoteModel (
    @SerializedName("text") val text: String?
        )