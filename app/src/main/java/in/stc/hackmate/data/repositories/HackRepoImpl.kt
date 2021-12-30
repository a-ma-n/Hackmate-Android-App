package `in`.stc.hackmate.data.repositories

import `in`.stc.hackmate.data.models.HackProfile
import `in`.stc.hackmate.data.models.RequestsResponse
import `in`.stc.hackmate.data.network.service.UserService
import `in`.stc.hackmate.ui.home.HomeActivity
import android.util.Log


private const val TAG = "HomeRepoImpl"

class HomeRepoImpl {

    companion object {
        private val ourInstance: HomeRepoImpl = HomeRepoImpl()

        fun getInstanceRepo(): HomeRepoImpl {
            return ourInstance
        }
    }

    val retroServiceHome = UserService.getRetroInstance().create(HackRepo::class.java)
    var map: HashMap<String, String> = HashMap()

    suspend fun fetchHacksRepo(): Result<List<HackProfile>?> {
        Log.i(TAG, "fetchHacksRepo: fetching hacks")
        val token:String = HomeActivity.idToken
        map["Authorization"] = token
        try {
            val response = retroServiceHome.fetchHacks(map)
            if (response.isSuccessful) {
                //todo: check this data & arraylist in response in interface
                Log.d(TAG, "fetchHacksRepo: " + response.body()?.toString())
                return Result.success(response.body()?.hack)
            } else {
                Log.d(TAG, "Its an error: ")
                Log.e(TAG, response.raw().toString())
                if(response.code()==401) {
                    HomeActivity.fetchIdToken()
                    return fetchHacksRepo()
                }
                return Result.failure(throw Exception(response.errorBody().toString()))
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    suspend fun fetchHacksByOngoing(): Result<List<HackProfile>?> {
        val token:String = HomeActivity.idToken
        map["Authorization"] = token
        try {
            val response = retroServiceHome.fetchHacksByUpcoming(map)
            return if (response.isSuccessful) {
                //todo: check this data & arraylist in response in interface
                Log.d(TAG, "fetchHacksRepo: " + response.body()?.toString())
                Result.success(response.body()?.hack)
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

    suspend fun fetchHacksByUpcoming(): Result<List<HackProfile>?> {
        val token:String = HomeActivity.idToken
        map["Authorization"] = token
        try {
            val response = retroServiceHome.fetchHacksByUpcoming(map)
            return if (response.isSuccessful) {
                //todo: check this data & arraylist in response in interface
                Log.d(TAG, "fetchHacksRepo: " + response.body()?.toString())
                Result.success(response.body()?.hack)
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

    suspend fun fetchHacksByPopularity(): Result<List<HackProfile>?> {
        val token:String = HomeActivity.idToken
        map["Authorization"] = token
        try {
            val response = retroServiceHome.fetchHacksByPopularity(map)
            return if (response.isSuccessful) {
                //todo: check this data & arraylist in response in interface
                Log.d(TAG, "fetchHacksRepo: " + response.body()?.toString())
                Result.success(response.body()?.hack)
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
