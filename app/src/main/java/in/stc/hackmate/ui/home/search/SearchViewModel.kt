package `in`.stc.hackmate.ui.home.search

import `in`.stc.hackmate.data.models.Team
import `in`.stc.hackmate.data.repositories.TeamRepoImpl
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

private const val TAG = "SearchViewModel"
class SearchViewModel:ViewModel() {
    private val repo: TeamRepoImpl = TeamRepoImpl.getInstanceRepo()
    var teams = MutableLiveData<List<Team>>()//change with list if we want

    fun fetchTeams(){
        Log.i(TAG, "fetchHacks: fetching hacks")
        viewModelScope.launch {
            if(repo.getAllTeams().isSuccess)
                teams.value = repo.getAllTeams().getOrNull()
        }
    }

}