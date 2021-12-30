package `in`.stc.hackmate.data.repositories

import `in`.stc.hackmate.data.models.Project
import `in`.stc.hackmate.data.models.ProjectResponse
import `in`.stc.hackmate.data.models.User
import `in`.stc.hackmate.data.models.UserResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ProjectRepo {

    @POST("projects/create")
    suspend fun create(@HeaderMap headers: HashMap<String,String>, @Body params: Project): Result<ProjectResponse>

    @GET("projects/get/{id}")
    fun byId(@HeaderMap headers: HashMap<String,String>, @Path("id") id :String): Call<ProjectResponse>

    @GET("projects/getAll?participant_id={id}")
    fun allProjects(@HeaderMap headers: HashMap<String,String>, @Path("id") id :String): Call<ProjectResponse>

    @PATCH("projects/update/{id}")
    fun updateById(@HeaderMap headers: HashMap<String,String>, @Path("id") id :String,@Body project:Project): Result<ProjectResponse?>

    @DELETE("projects/delete/{id}")
    fun delById(@HeaderMap headers: HashMap<String,String>, @Path("id") id :String): Result<ProjectResponse>

}