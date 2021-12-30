package `in`.stc.hackmate.data.repositories

import `in`.stc.hackmate.data.models.Project
import `in`.stc.hackmate.data.models.Team
import `in`.stc.hackmate.data.models.UserResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface TeamRepo {

    @POST("Dn_Team/createTeam/{id}")
    suspend fun create(@HeaderMap headers: HashMap<String,String>, @Body params: String): Call<UserResponse>

    @POST("Dn_Team/code/null}")
    suspend fun joinCode(@HeaderMap headers: HashMap<String,String>, @Body code: String): Call<UserResponse>

    @GET("DN_Team/admin/{id}")
    suspend fun getAdminTeams(@HeaderMap headers: HashMap<String,String>, @Path("id") id :String):Response<List<Team>>

    //check this route
    @GET("DN_Team/teamName/{id}?name=hehe")
    suspend fun getByName(@HeaderMap headers: HashMap<String,String>, @Path("id") id :String): Response<List<Team>>


    @GET("DN_Team/teamSkills/60f1cacb03a7ce0015c93f6b?page=1&skill={skillId}")
    suspend fun getBySkills(@HeaderMap headers: HashMap<String,String>, @Path("skillId") skill :String): Response<List<Team>>

    @POST("DN_Team/addSkills/6118043a728b472bdc79e24d")
    suspend fun addSkills(@HeaderMap headers: HashMap<String,String>, @Body skills :ArrayList<String>): Call<UserResponse>

    //todo: array list of strings? & implement paging adapter
    @GET("DN_Team/getSkills/{id}")
    suspend fun getBySkills(@HeaderMap headers: HashMap<String,String>):  Response<List<Team>>

    @PATCH("DN_Team/update/60fe89753dc96e001575eddc")
    suspend fun updateTeam(@HeaderMap headers: HashMap<String,String>, @Body team :Team): Call<UserResponse>

    @DELETE("DN_Team/deleteTeam/610d7741613542001562a56f")
    suspend fun delById(@HeaderMap headers: HashMap<String,String>): Call<UserResponse>

    @DELETE("DN_Team/deleteTeam/610d7741613542001562a56f")
    suspend fun leaveTeam(@HeaderMap headers: HashMap<String,String>): Call<UserResponse>

    @PATCH("DN_Team/removeMember/60f25b10feefa334ace5fe7e/60f13063ce79400015732285")
    suspend fun removeFromTeam(@HeaderMap headers: HashMap<String,String>, @Body team :Team): Call<UserResponse>

    @GET("DN_Team/all/null?page=1")
    suspend fun getAllTeams(@HeaderMap headers: HashMap<String,String>):  Response<List<Team>>

    @GET("DN_Team/{id}")
    suspend fun getById(@HeaderMap headers: HashMap<String,String>,@Path("id") id:String):  Response<List<Team>>

    @GET("DN_Team/myTeams?page=1")
    suspend fun myTeams(@HeaderMap headers: HashMap<String,String>):  Response<List<Team>>


}