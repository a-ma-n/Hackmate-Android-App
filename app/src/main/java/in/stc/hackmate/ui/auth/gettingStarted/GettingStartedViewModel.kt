package `in`.stc.hackmate.ui.auth.gettingStarted

import `in`.stc.hackmate.data.models.User
import `in`.stc.hackmate.data.repositories.ProfileRepoImpl
import `in`.stc.hackmate.data.repositories.SkillsRepoImpl
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class GettingStartedViewModel:ViewModel() {

    var TAG="GettingStartedViewModel"
    private val repo: ProfileRepoImpl = ProfileRepoImpl.getInstanceRepo()
    private val repo2: SkillsRepoImpl = SkillsRepoImpl.getInstanceRepo()

    fun checkUserNameViewModel(uname:String)
    {
        viewModelScope.launch {
            repo.checkUserNameRepo(uname)
        }
    }
    fun createUserViewModel(user:User)//uname:String,
    {
        viewModelScope.launch {
          repo.createUserRepo(user)
        }
    }

    fun addSkillsViewModel()
    {
        viewModelScope.launch {
            repo2.addSkills()
        }
    }



}