package `in`.stc.hackmate.ui.home.teams

import `in`.stc.hackmate.data.models.HackProfile
import `in`.stc.hackmate.data.models.Team
import `in`.stc.hackmate.data.repositories.HomeRepoImpl
import `in`.stc.hackmate.data.repositories.TeamRepoImpl
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

private const val TAG = "MyTeamsViewModel"
class MyTeamsViewModel:ViewModel() {
    private val repo: TeamRepoImpl = TeamRepoImpl.getInstanceRepo()
    var teams = MutableLiveData<List<Team>>()//change with list if we want

    fun fetchTeams(){
        Log.i(TAG, "fetchHacks: fetching hacks")
        viewModelScope.launch {
            if(repo.myTeams().isSuccess)
                teams.value = repo.myTeams().getOrNull()
        }
    }
}