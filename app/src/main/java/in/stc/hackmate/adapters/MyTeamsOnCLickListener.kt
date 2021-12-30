package `in`.stc.hackmate.adapters

import `in`.stc.hackmate.data.models.HackProfile
import `in`.stc.hackmate.data.models.Team
import android.view.View

interface MyTeamsOnCLickListener {
    fun onClick(view: View, position: Int, team: Team)

}