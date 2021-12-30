package `in`.stc.hackmate.data.repositories

import `in`.stc.hackmate.data.models.SkillsResponse
import `in`.stc.hackmate.data.models.UserResponse
import `in`.stc.hackmate.data.network.service.UserService
import `in`.stc.hackmate.ui.home.profile.EditProfileFragment
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface SkillsRepo {

    // Skills
    //todo: fix skills 417 error

    @POST("skills/mySkills")
    suspend fun addSkills(@HeaderMap headers: HashMap<String,String>, @Body params: MutableList<String> ): Response<SkillsResponse>

    @GET
    fun getSkills(@HeaderMap headers: HashMap<String,String>): Response<List<SkillsResponse>>


}