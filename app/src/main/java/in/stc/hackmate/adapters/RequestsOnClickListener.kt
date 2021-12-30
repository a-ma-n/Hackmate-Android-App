package `in`.stc.hackmate.adapters

import `in`.stc.hackmate.data.models.InvitesReceived
import `in`.stc.hackmate.data.models.InvitesSent
import `in`.stc.hackmate.data.models.Team
import android.view.View

interface RequestsOnClickListener {
    fun onClick(view: View, position: Int, requests: InvitesSent,accept:Boolean)

}