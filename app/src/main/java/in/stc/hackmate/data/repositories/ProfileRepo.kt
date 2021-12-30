package `in`.stc.hackmate.data.repositories

import `in`.stc.hackmate.data.models.User
import `in`.stc.hackmate.data.models.UserResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ProfileRepo {
    @GET("participant/login")
    suspend fun loginUser(@HeaderMap headers: HashMap<String,String>): Call<UserResponse>

    @GET("participant/login")
    suspend fun loginUserForProfile(@HeaderMap headers: HashMap<String,String>): Response<User>

    @POST("participant/checkUserName/{username}")
    fun checkNameUser(@HeaderMap headers: HashMap<String,String>, @Path ("username") uname :String): Call<UserResponse>

    @POST("participant/createProfile")
    fun createUser(@HeaderMap headers: HashMap<String,String>, @Body params: User): Call<UserResponse>

    @POST("participant/signup")
    fun setclaim(@HeaderMap headers: HashMap<String,String> ): Call<UserResponse>

    //todo: implement update and delete profile

    @PATCH
    fun updateProfile(@HeaderMap headers: HashMap<String,String>, @Body params: User): Response<User>

    @DELETE
    suspend fun deleteProfile(@HeaderMap headers: HashMap<String,String>): Call<UserResponse>

}

