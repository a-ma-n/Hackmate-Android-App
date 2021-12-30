package `in`.stc.hackmate.data.repositories

import `in`.stc.hackmate.data.models.Project
import `in`.stc.hackmate.data.models.ProjectResponse
import `in`.stc.hackmate.data.models.User
import `in`.stc.hackmate.data.models.UserResponse
import `in`.stc.hackmate.data.network.service.UserService
import `in`.stc.hackmate.ui.auth.gettingStarted.GettingStartedFragment
import `in`.stc.hackmate.ui.auth.login.LoginFragment
import `in`.stc.hackmate.ui.auth.signin.SignupFragment
import `in`.stc.hackmate.ui.home.HomeActivity
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "ProfileRepoImpl"
  class ProfileRepoImpl {
      companion object {
          private val ourInstance: ProfileRepoImpl = ProfileRepoImpl()

          fun getInstanceRepo(): ProfileRepoImpl {
              return ourInstance
          }
          var responseCodeLogin:Int=0
      }

      val retroService = UserService.getRetroInstance().create(ProfileRepo::class.java)
      var map: HashMap<String, String> = HashMap()
      var flag: Boolean = false


      suspend fun setclaimRepo()=withContext(Dispatchers.Default) {


          val call = retroService.setclaim(map)
          call.enqueue(object : Callback<UserResponse> {
              override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                  Log.e(TAG, "setclaim failed 1: ")
                  Log.e(TAG, t.toString())
                  Log.e(TAG, call.toString())
                  Log.e(TAG, "onFailure: ", t)
                  flag = false
              }

              override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                  if (response.isSuccessful) {
                      Log.d(TAG, "set claim done ")
                      Log.d(TAG, "onResponse: " + response.code().toString())
                      flag = true
                  } else {
                      response
                      Log.e(TAG, "set claim  failed 2 ")
                      Log.e(TAG, "Auth token: ")
                      Log.e(TAG, SignupFragment.idToken.toString())
                      //Log.e(TAG, LoginFragment.idTokenLogin.toString())
                      Log.e(TAG, response.code().toString())
                      Log.e(TAG, response.body().toString())
                      flag = false
                  }


              }
          })

      }

      suspend fun loginUserRepo()= withContext(Dispatchers.Default) {

          Log.e(TAG, "token is " +LoginFragment.idTokenLogin)
          map["Authorization"] = LoginFragment.idTokenLogin

              val call = retroService.loginUser(map)
              call.enqueue(object : Callback<UserResponse> {
                  override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                      Log.e(TAG, "User Creation failed 1: ")
                      Log.e(TAG, t.toString())
                      Log.e(TAG, call.toString())
                      Log.e(TAG, "onFailure: ", t)
                  }

                  override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                      if (response.isSuccessful) {
                          Log.d(TAG, "User Creation done ")
                          Log.d(TAG, "onResponse: " + response.code().toString())
                          responseCodeLogin=response.code()
                      } else {

                          Log.e(TAG, "User Creation failed 2 ")
                          Log.e(TAG, "Auth token: ")
                          responseCodeLogin=response.code()
                          Log.e(TAG, LoginFragment.idTokenLogin.toString())
                          Log.e(TAG, response.code().toString())
                          Log.e(TAG, response.body().toString())
                      }


                  }
              })
          }

      suspend fun checkUserNameRepo(uname: String) = withContext(Dispatchers.Default) {

          map.put("Authorization", LoginFragment.idTokenLogin.toString())

          val call = retroService.checkNameUser(map, uname)
          call.enqueue(object : Callback<UserResponse> {
              override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                  Log.e(TAG, "Username not unique ")
                  Log.e(TAG, t.toString())
                  Log.e(TAG, call.toString())
                  flag = false
              }
              override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                  if (response.isSuccessful) {
                      Log.d(TAG, "Username unique  ")
                      flag = true
                  } else {
                      Log.e(TAG, "User name not uninque kuch bt  ")
                      Log.e(TAG, "Auth token in : ")
                      Log.e(TAG, LoginFragment.idTokenLogin.toString())
                      Log.e(TAG, response.code().toString())
                      Log.e(TAG, response.body().toString())
                      Log.e(TAG, response.message().toString())
                      Log.e(TAG, response.errorBody().toString())
                      Log.e(TAG, response.raw().toString())
                      flag = false
                  }
              }
          })
      }

      suspend fun createUserRepo(user: User) = withContext(Dispatchers.Default) {
          map.put("Authorization", LoginFragment.idTokenLogin.toString())
          val call = retroService.createUser(map, user)

          call.enqueue(object : Callback<UserResponse> {
              override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                  Log.e(TAG+"create user", "User Creation failed 1: ")
                  Log.e(TAG+"create user", t.toString())
                  Log.e(TAG+"create user", call.toString())
              }

              override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                  if (response.isSuccessful) {
                      Log.d(TAG+"create user", "User Creation done ")
                      Log.e(TAG+"create user", response.body().toString())
                  } else {
                      response
                      Log.e(TAG+"create user", "User Creation failed 2 ")
                      Log.e(TAG+"create user", "Auth token: ")
                      Log.e(TAG+"create user", LoginFragment.idTokenLogin.toString())
                      Log.e(TAG+"create user", response.code().toString())
                      Log.e(TAG+"create user", response.body().toString())
                  }
              }
          })
      }

      suspend fun deleteUserRepo()= withContext(Dispatchers.Default) {

        val token:String = HomeActivity.idToken
        map["Authorization"] = token


        try {
            val response= retroService.deleteProfile(map)
            Log.d(TAG, "deluser: " + response.toString())

        } catch (e: Exception) {
              Log.e(TAG, "loginUserRepo: " ,e )
          }

    }

    suspend fun loginUserRepoForProfile():Result<User?> {

        Log.e(TAG, "token is " +LoginFragment.idTokenLogin)
        map["Authorization"] = LoginFragment.idTokenLogin

        try {
            val response = retroService.loginUserForProfile(map)
            return if (response.isSuccessful) {
                //todo: check this data & arraylist in response in interface
                Log.d(TAG, "fetchHacksRepo: " + response.body()?.toString())
                Result.success(response.body())
            } else {
                Log.d(TAG, "Its an error: ")
                Log.e(TAG, response.raw().toString())
                Result.failure(throw Exception(response.errorBody().toString()))
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }

    }

    suspend fun updateUserProfile(user:User):Result<User?> {

        val token:String = HomeActivity.idToken
        map["Authorization"] = token


        try {
            val response= retroService.updateProfile(map,user)
            return if(response.isSuccessful) {
                Log.d(TAG, "updateUser: " + response.toString())
                Result.success(response.body())
            }
            else{
                Result.failure(throw Exception(response.errorBody().toString()))
            }
        } catch (e: Exception) {
            Log.e(TAG, "updateUser: " ,e )
            return Result.failure(e)
        }
    }



  }


