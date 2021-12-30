package `in`.stc.hackmate.data.repositories

import `in`.stc.hackmate.data.models.Team
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Path

interface ParticipantRepo {

    @GET("participant/get/skill/null?page=1&skill=frontend")
    suspend fun getBySkills(@HeaderMap headers: HashMap<String,String>, @Path("id") id:String): Response<List<Team>>

    @GET("participant/get/all/60f1cacb03a7ce0015c93f6b?page=1")
    suspend fun getAllForHack(@HeaderMap headers: HashMap<String,String>): Response<List<Team>>

    @GET("participant/get/:participant_id")
    suspend fun getById(@HeaderMap headers: HashMap<String,String>, @Path("id") id:String): Response<List<Team>>

    @GET("participant/get/userName/null?name=ch&page=1")
    suspend fun getByUsername(@HeaderMap headers: HashMap<String,String>, @Path("id") id:String): Response<List<Team>>

}