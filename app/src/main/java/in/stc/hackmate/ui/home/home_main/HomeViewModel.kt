package `in`.stc.hackmate.ui.home.home_main

import `in`.stc.hackmate.data.models.HackProfile
import `in`.stc.hackmate.data.repositories.HomeRepoImpl
import `in`.stc.hackmate.ui.home.HomeActivity
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

private const val TAG = "HomeViewModel"
class HomeViewModel :ViewModel(){

    private val repo: HomeRepoImpl = HomeRepoImpl.getInstanceRepo()
    var hacks = MutableLiveData<List<HackProfile>>()//change with list if we want

    fun fetchHacks(){
        Log.i(TAG, "fetchHacks: fetching hacks")
           viewModelScope.launch {
               if(repo.fetchHacksRepo().isSuccess)
                   hacks.value = repo.fetchHacksRepo().getOrNull()
           }
    }

    fun fetchHacksByOngoing(){
        viewModelScope.launch {
            if(repo.fetchHacksByOngoing().isSuccess)
                hacks.value = repo.fetchHacksByOngoing().getOrNull()
        }
    }

    fun fetchHacksByUpcoming(){
        viewModelScope.launch {
            if(repo.fetchHacksByUpcoming().isSuccess)
                hacks.value = repo.fetchHacksByUpcoming().getOrNull()
        }
    }

    fun fetchHacksByPopularity(){
        viewModelScope.launch {
            if(repo.fetchHacksByPopularity().isSuccess)
                hacks.value = repo.fetchHacksByPopularity().getOrNull()
        }
    }

}
