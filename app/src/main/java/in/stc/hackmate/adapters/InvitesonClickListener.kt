package `in`.stc.hackmate.adapters

import `in`.stc.hackmate.data.models.InvitesReceived
import `in`.stc.hackmate.data.models.Team
import android.view.View

interface InvitesonClickListener {
    fun onClick(view: View, position: Int, invite: InvitesReceived,accept:Boolean)

}