package `in`.stc.hackmate.ui.home.team_create

import `in`.stc.hackmate.data.models.Team
import `in`.stc.hackmate.data.models.User
import `in`.stc.hackmate.data.repositories.ProfileRepoImpl
import `in`.stc.hackmate.data.repositories.TeamRepoImpl
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

private const val TAG = "TeamCreateViewModel"

class TeamCreateViewModel:ViewModel() {


    private val repoProfile: ProfileRepoImpl = ProfileRepoImpl.getInstanceRepo()
    private val repoTeam: TeamRepoImpl = TeamRepoImpl.getInstanceRepo()

    private lateinit var user: User//change with list if we want

    fun fetchUserDetails():User{
        viewModelScope.launch {
            if(repoProfile.loginUserRepoForProfile().isSuccess)//change to get participant details
                user = repoProfile.loginUserRepoForProfile().getOrNull()!!
        }
        return user
    }

     fun createTeam(team: Team,chips:  MutableList<String>){
        viewModelScope.launch {
          repoTeam.create(team,chips)

            //todo: 2 routes 1-> create team, 2->add skills
        }
    }

    }