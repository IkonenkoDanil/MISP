package ru.viknist.emotionsrest.network

import retrofit2.http.*
import ru.viknist.emotionsrest.models.*

interface RestApi {

    @POST("auth/login/")
    suspend fun loginUser(@Body loginInfo: UserData): UserToken

    @POST("auth/register/")
    suspend fun registerUser(@Body registerInfo: RegistrationModel): UserToken

    @POST("auth/password/change/")
    suspend fun changePassword(
        @Body passwordInfo: Password,
        @Header("Authorization") authHeader: String
    )

    @PATCH("auth/user/")
    suspend fun changeEmailName(
        @Body userData: PatchUserData,
        @Header("Authorization") authHeader: String
    )

    @GET("auth/user/")
    suspend fun getUserInfo(@Header("Authorization") authHeader: String): UserData

    @POST("app/post_note/")
    suspend fun postNote(
        @Body emotion: EmotionModel,
        @Header("Authorization") authHeader: String
    )

    @GET("app/days/stats/")
    suspend fun getStats(
        @Header("Authorization") authHeader: String
    ): List<StatsModel>

    @GET("app/days/stats/{id}/")
    suspend fun getStatFromId(
        @Path("id") id: Int,
        @Header("Authorization") authHeader: String
    ): StatsModel

    @PATCH("app/days/stats/{id}/")
    suspend fun patchNote(
        @Path("id") id: Int,
        @Body text: PatchNoteModel,
        @Header("Authorization") authHeader: String
    ): StatsModel
}