package `in`.stc.hackmate.ui.home.team_add_from_existing

import `in`.stc.hackmate.data.models.HackProfile
import `in`.stc.hackmate.data.models.Team
import `in`.stc.hackmate.data.repositories.HomeRepoImpl
import `in`.stc.hackmate.data.repositories.TeamRepoImpl
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

private const val TAG = "AddFromExistingViewModel"
class AddFromExistingViewModel:ViewModel() {


    private val repo: TeamRepoImpl = TeamRepoImpl.getInstanceRepo()
    var teams = MutableLiveData<List<Team>>()

    fun fetchHacks(){
        Log.i(TAG, "fetching my teams")
        viewModelScope.launch {
            if(repo.myTeams().isSuccess)
                teams.value = repo.myTeams().getOrNull()
        }
    }

    fun addTeam(team: Team, hack: Any) {
        Log.i(TAG, "updating my teams")
        viewModelScope.launch {
//            if(repo.myTeams().isSuccess)
//                teams.value = repo.myTeams().getOrNull()

    }
}
}