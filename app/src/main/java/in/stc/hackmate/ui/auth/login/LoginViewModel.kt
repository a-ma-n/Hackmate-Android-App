package `in`.stc.hackmate.ui.auth.login

import `in`.stc.hackmate.data.repositories.ProfileRepoImpl
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    var TAG="LoginViewModel"
    private val repo: ProfileRepoImpl = ProfileRepoImpl.getInstanceRepo()

    fun loginViewmodel()
    {
        viewModelScope.launch {
            repo.loginUserRepo()
        }
    }
}