package `in`.stc.hackmate.ui.home.deleteAccount

import `in`.stc.hackmate.data.repositories.ProfileRepoImpl
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class DeleteAccountViewModel:ViewModel() {
    private val repo: ProfileRepoImpl = ProfileRepoImpl.getInstanceRepo()

    fun deleteUser()
    {
        viewModelScope.launch {
            repo.deleteUserRepo()
        }
    }
}