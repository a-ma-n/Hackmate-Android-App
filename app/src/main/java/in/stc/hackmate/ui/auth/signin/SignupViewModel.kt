package `in`.stc.hackmate.ui.auth.signin

import `in`.stc.hackmate.data.repositories.ProfileRepoImpl
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SignupViewModel:ViewModel() {
    var TAG="SignupViewModel"
    private val repo: ProfileRepoImpl = ProfileRepoImpl.getInstanceRepo()

    fun setclaimViewmodel()//uname:String,
    {
        var flag:Boolean=false
        viewModelScope.launch {
            repo.setclaimRepo()
        }
    }
}