package `in`.stc.hackmate.data.repositories

import `in`.stc.hackmate.data.models.HackList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Path

interface HackRepo {

    @GET("getHacks/all?page=1")
    suspend fun fetchHacks(@HeaderMap headers: HashMap<String,String>): Response<HackList>

    @GET("getHacks/popularity?page=1")
    suspend fun fetchHacksByPopularity(@HeaderMap headers: HashMap<String,String>): Response<HackList>

    @GET("getHacks/ongoing?page=1")
    suspend fun fetchHacksByOngoing(@HeaderMap headers: HashMap<String,String>): Response<HackList>

    @GET("getHacks/upcoming?page=1")
    suspend fun fetchHacksByUpcoming(@HeaderMap headers: HashMap<String,String>): Response<HackList>

    @GET("getHacks/{hackId}")
    suspend fun fetchHacksById(@HeaderMap headers: HashMap<String,String>, @Path("hack_id") hackId :String): Response<HackList>


}