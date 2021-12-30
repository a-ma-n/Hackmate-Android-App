package `in`.stc.hackmate.adapters

import `in`.stc.hackmate.data.models.Team
import android.view.View

interface FindATeamOnClickListener {
    fun onClick(view: View, position: Int, team: Team)


}