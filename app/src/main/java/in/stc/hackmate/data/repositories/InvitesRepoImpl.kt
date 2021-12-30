package `in`.stc.hackmate.data.repositories

import `in`.stc.hackmate.data.models.Invites
import `in`.stc.hackmate.data.models.InvitesReceived
import `in`.stc.hackmate.data.network.service.UserService
import `in`.stc.hackmate.ui.home.HomeActivity
import android.util.Log

private const val TAG = "InvitesRepoImpl"
class InvitesRepoImpl {
    companion object {
        private val ourInstance: InvitesRepoImpl = InvitesRepoImpl()

        fun getInstanceRepo(): InvitesRepoImpl {
            return ourInstance
        }
        var responseCodeLogin:Int=0
    }

    val retroService = UserService.getRetroInstance().create(InvitesRepo::class.java)
    var map: HashMap<String, String> = HashMap()

    suspend fun fetchInvites():Result<List<InvitesReceived>>{
        try {
            val response = retroService.getMyInv(map)
            return if (response.isSuccessful) {
                //todo: check this data & arraylist in response in interface
                Log.d(TAG, "fetchHacksRepo: " + response.body()?.toString())
                Result.success(response.body()!!)
            } else {

                Result.failure(throw Exception(response.errorBody().toString()))
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }

    }
    suspend fun responseInvites(s:String){
        val token:String = HomeActivity.idToken
        map["Authorization"] = token
        try {
            //todo pass the id??
            val response= retroService.statusInv(map,s)
            Log.d(TAG, "deluser: " + response.toString())

        } catch (e: Exception) {
            Log.e(TAG, "loginUserRepo: " ,e )
        }
    }
}