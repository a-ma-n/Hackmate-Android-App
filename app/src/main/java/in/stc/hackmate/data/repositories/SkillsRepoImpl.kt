package `in`.stc.hackmate.data.repositories
import `in`.stc.hackmate.data.models.SkillsResponse
import `in`.stc.hackmate.data.models.UserResponse
import `in`.stc.hackmate.data.network.service.UserService
import `in`.stc.hackmate.ui.auth.gettingStarted.GettingStartedFragment
import `in`.stc.hackmate.ui.auth.login.LoginFragment
import `in`.stc.hackmate.ui.home.HomeActivity
import `in`.stc.hackmate.ui.home.profile.EditProfileFragment
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "SkillsRepoImpl"

class SkillsRepoImpl {
    companion object {
        private val ourInstance: SkillsRepoImpl = SkillsRepoImpl()

        fun getInstanceRepo(): SkillsRepoImpl {
            return ourInstance
        }
        var responseCodeLogin:Int=0
    }

    val retroService = UserService.getRetroInstance().create(SkillsRepo::class.java)
    var map: HashMap<String, String> = HashMap()

    suspend fun addSkills():Result<SkillsResponse> = withContext(Dispatchers.Default){
        val token:String = HomeActivity.idToken
        map["Authorization"] = token
        try {
            val response = retroService.addSkills(map,EditProfileFragment.checkedChips)
            return@withContext Result.success(response.body()!!)
        } catch (e: Exception) {
            Log.e(TAG, "updateUser: " ,e )
            return@withContext Result.failure(e)
        }
    }

    suspend fun getSkills():Result<List<SkillsResponse>>{
        map.put("Authorization", LoginFragment.idTokenLogin)
        Log.d(TAG+"add skills", "addSkillsRepo: ")
        val token:String = HomeActivity.idToken
        map["Authorization"] = token
        try {
            val response = retroService.getSkills(map)
            return if (response.isSuccessful) {
                //todo: check this data & arraylist in response in interface
                Log.d(TAG, "fetchHacksRepo: " + response.toString())
                Result.success(response.body()!!)
            } else {
                Log.d(TAG, "Its an error: ")
                Log.e(TAG, response.body().toString())
                Log.e(TAG, response.errorBody().toString())
                Log.e(TAG, response.message().toString())
                Log.e(TAG, response.raw().toString())
                Result.failure(throw Exception(response.errorBody().toString()))
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }


    }


}