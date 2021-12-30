package `in`.stc.hackmate.data.repositories

import `in`.stc.hackmate.data.models.HackProfile
import `in`.stc.hackmate.data.models.Team
import `in`.stc.hackmate.data.models.TeamResponse
import `in`.stc.hackmate.data.models.User
import `in`.stc.hackmate.data.network.service.UserService
import `in`.stc.hackmate.ui.home.HomeActivity
import android.util.Log

private const val TAG = "TeamRepoImpl"
class TeamRepoImpl {
    companion object {
        private val ourInstance: TeamRepoImpl = TeamRepoImpl()

        fun getInstanceRepo(): TeamRepoImpl {
            return ourInstance
        }
        var responseCodeLogin:Int=0
    }

    val retroService = UserService.getRetroInstance().create(TeamRepo::class.java)
    var map: HashMap<String, String> = HashMap()

    suspend fun create(team: Team,chips:MutableList<String>) {

        val token:String = HomeActivity.idToken
        map["Authorization"] = token


        try {
            //todo add skills also as it is a companion obj of the class
            val response = retroService.create(map, team.name)

            Log.d(TAG, "team: " + response.toString())

            val response2 = retroService.addSkills(map, chips as ArrayList<String>)
            Log.d(TAG, "team: " + response2.toString())


        } catch (e: Exception) {
            Log.e(TAG, "error: " ,e )
        }
    }

    suspend fun myTeams():Result<List<Team>?>{
        val token:String = HomeActivity.idToken
        map["Authorization"] = token
        try {
            val response = retroService.myTeams(map)
            return if (response.isSuccessful) {
                //todo: check this data & arraylist in response in interface
                Log.d(TAG, "fetchHacksRepo: " + response.body()?.toString())
                Log.d(TAG, "myTeams: "+response.body())
                Result.success(response.body())
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

    //todo: fn to get all teams
    suspend fun getAllTeams():Result<List<Team>?>{
        val token:String = HomeActivity.idToken
        map["Authorization"] = token
        try {
            val response = retroService.getAllTeams(map)
            return if (response.isSuccessful) {
                //todo: check this data & arraylist in response in interface
                Log.d(TAG, "fetchHacksRepo: " + response.body()?.toString())
                Log.d(TAG, "all teams: "+response.body())
                Result.success(response.body())
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