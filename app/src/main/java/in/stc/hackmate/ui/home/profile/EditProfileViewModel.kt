package `in`.stc.hackmate.ui.home.profile

import `in`.stc.hackmate.data.models.Project
import `in`.stc.hackmate.data.models.User
import `in`.stc.hackmate.data.repositories.ProfileRepoImpl
import `in`.stc.hackmate.data.repositories.ProjectRepoImpl
import `in`.stc.hackmate.data.repositories.SkillsRepoImpl
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

private const val TAG = "EditProfileViewModel"
class EditProfileViewModel: ViewModel() {
    private val repoProfile: ProfileRepoImpl = ProfileRepoImpl.getInstanceRepo()
    private val repoSkills: SkillsRepoImpl = SkillsRepoImpl.getInstanceRepo()
    private val repoProject: ProjectRepoImpl = ProjectRepoImpl.getInstanceRepo()

    private lateinit var user: User//change with list if we want

    fun updateUser(userGiven: User){
        viewModelScope.launch {
            if(repoProfile.updateUserProfile(user).isSuccess)//change to get participant details
                user = repoProfile.loginUserRepoForProfile().getOrNull()!!
        }

    }

    fun addSkills(){
        viewModelScope.launch {
            repoSkills.addSkills()//change to get participant details
    }
    }

    fun deleteproject(project: Project) {
        viewModelScope.launch {
            repoProject.deleteProject(project)//change to get participant details
        }
    }

}