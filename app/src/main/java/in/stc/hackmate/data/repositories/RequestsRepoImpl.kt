package `in`.stc.hackmate.data.repositories

import `in`.stc.hackmate.data.models.HackProfile
import `in`.stc.hackmate.data.models.InvitesSent
import `in`.stc.hackmate.data.models.Requests
import `in`.stc.hackmate.data.network.service.UserService
import `in`.stc.hackmate.ui.home.HomeActivity
import android.util.Log

private const val TAG = "RequestsRepoImpl"
class RequestsRepoImpl {
    companion object {
        private val ourInstance: RequestsRepoImpl = RequestsRepoImpl()

        fun getInstanceRepo(): RequestsRepoImpl {
            return ourInstance
        }
        var responseCodeLogin:Int=0
    }

    val retroService = UserService.getRetroInstance().create(RequestsRepo::class.java)
    var map: HashMap<String, String> = HashMap()

    suspend fun fetchRequests():Result<List<InvitesSent>>{
        val token:String = HomeActivity.idToken
        map["Authorization"] = token
        try {
            val response = retroService.getMyReq(map)
            return if (response.isSuccessful) {
                Log.d(TAG, "fetchHacksRepo: " + response.body()?.toString())
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

    //todo figure this route out
    suspend fun responseRequests(s:String){
        val token:String = HomeActivity.idToken
        map["Authorization"] = token
        try {
            //todo pass the id??
            val response= retroService.statusReq(map,s)
            Log.d(TAG, "deluser: " + response.toString())

        } catch (e: Exception) {
            Log.e(TAG, "loginUserRepo: " ,e )
        }
    }

}