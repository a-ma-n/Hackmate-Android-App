package `in`.stc.hackmate.ui.home.requestsandinvites

import `in`.stc.hackmate.data.models.*
import `in`.stc.hackmate.data.repositories.HomeRepoImpl.Companion.getInstanceRepo
import `in`.stc.hackmate.data.repositories.InvitesRepoImpl
import `in`.stc.hackmate.data.repositories.ProjectRepoImpl
import `in`.stc.hackmate.data.repositories.RequestsRepoImpl
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

private const val TAG = "RequestAndInviteViewModel"
class RequestAndInviteViewModel:ViewModel() {
    private val repoRequests: RequestsRepoImpl = RequestsRepoImpl.getInstanceRepo()
    private val repoInvites: InvitesRepoImpl = InvitesRepoImpl.getInstanceRepo()
    var invites = MutableLiveData<List<InvitesReceived>>()//change with list if we want
    var requests = MutableLiveData<List<InvitesSent>>()
    fun getRequests(){
        viewModelScope.launch {
            if(repoRequests.fetchRequests().isSuccess)//change to get participant details
            {
                requests.value = repoRequests.fetchRequests().getOrNull()
                Log.d(TAG, ": successful")
            }
            else
                Log.e(TAG, ": couldnt fetch a requests")
        }
    }
    fun getInvites(){
        viewModelScope.launch {
            if(repoInvites.fetchInvites().isSuccess)//change to get participant details
            {
                invites.value = repoInvites.fetchInvites().getOrNull()
                Log.d(TAG, ": successful")
            }
            else
                Log.e(TAG, ": couldnt fetch a invites")
        }

    }

    fun responseInvites(s: String) {
        viewModelScope.launch {
            repoInvites.responseInvites(s)//change to get participant details
        }
    }

    fun responseRequests(s: String) {
        viewModelScope.launch {
            repoRequests.responseRequests(s)//change to get participant details
        }
    }


}