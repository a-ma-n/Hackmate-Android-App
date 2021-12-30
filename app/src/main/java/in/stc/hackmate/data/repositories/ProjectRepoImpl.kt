package `in`.stc.hackmate.data.repositories

import `in`.stc.hackmate.data.models.Project
import `in`.stc.hackmate.data.models.ProjectResponse
import `in`.stc.hackmate.data.models.User
import `in`.stc.hackmate.data.models.UserResponse
import `in`.stc.hackmate.data.network.service.UserService
import `in`.stc.hackmate.ui.home.HomeActivity
import android.util.Log
import retrofit2.Call

private const val TAG = "ProjectRepoImpl"
class ProjectRepoImpl {
    companion object {
        private val ourInstance: ProjectRepoImpl = ProjectRepoImpl()

        fun getInstanceRepo(): ProjectRepoImpl {
            return ourInstance
        }
        var responseCodeLogin:Int=0
    }

    val retroService = UserService.getRetroInstance().create(ProjectRepo::class.java)
    var map: HashMap<String, String> = HashMap()
    var flag: Boolean = false

    suspend fun addProject(project: Project): Result<ProjectResponse> {

        val token:String = HomeActivity.idToken
        map["Authorization"] = token
        try {
            var response = retroService.create(map,project)
            return response

        } catch (e: Exception) {
            Log.e(TAG, "updateUser: " ,e )
            return retroService.create(map,project)
        }
    }

    //todo :finish edit project route > teams s update karna h?

    //todo:dummy fn implement this route properly

   suspend fun editProject(project: Project): Result<ProjectResponse?> {

        val token:String = HomeActivity.idToken
        map["Authorization"] = token
        try {
            //todo: add path in user project
            var response = retroService.updateById(map,project.id,project)
            return response
        } catch (e: Exception) {
            Log.e(TAG, "updateUser: " ,e )
        }
        return retroService.create(map,project)

   }
    fun deleteProject(project: Project):Result<ProjectResponse?> {
        val token:String = HomeActivity.idToken
        map["Authorization"] = token


        try {
            var response = retroService.updateById(map,project.id,project)
            return response
        } catch (e: Exception) {
            Log.e(TAG, "updateUser: " ,e )
            return Result.failure(e)
        }
    }

}