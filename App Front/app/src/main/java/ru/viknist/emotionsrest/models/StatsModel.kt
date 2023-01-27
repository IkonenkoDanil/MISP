package ru.viknist.emotionsrest.models

import com.google.gson.annotations.SerializedName

data class StatsModel(
    @SerializedName("id") val id: Int?,
    @SerializedName("text") val text: String?,
    @SerializedName("date") val date: String?,
    @SerializedName("overall_emotion") val overallEmotion: String?,
    @SerializedName("notes") val notes: List<NoteModel>?
)
