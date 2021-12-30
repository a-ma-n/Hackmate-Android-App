package `in`.stc.hackmate.ui.home.profile

import `in`.stc.hackmate.data.models.User
import `in`.stc.hackmate.data.repositories.ProfileRepoImpl
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

private const val TAG = "ProfileViewModel"

class ProfileViewModel:ViewModel() {
    companion object{
        var uid:String?=""
    }


    private val repo: ProfileRepoImpl = ProfileRepoImpl.getInstanceRepo()
    private var user: User? = null//change with list if we want

    fun fetchUserDetails():User?{
        viewModelScope.launch {
            if(repo.loginUserRepoForProfile().isSuccess)//change to get participant details
                user = repo.loginUserRepoForProfile().getOrNull()!!
             uid = user?.id
            Log.d(TAG, "userid: "+uid)
        }
        return user
    }

}
