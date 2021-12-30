package `in`.stc.hackmate.data.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName



//todo fix the inivites and requests data model
data class Invites (
    @SerializedName("received")  val received: List<InvitesReceived>? = null,
    @SerializedName("sent")  val sent: List<InvitesSent>? = null
)
data class InvitesResponse (
    @SerializedName("code")val code: Int?,
    @SerializedName("meta")val meta: String?,
    @SerializedName("data")val data: Invites?
    )

data class InvitesReceived (
    @SerializedName("inv") val id:String,
    @SerializedName("participant") val participant:User,
    @SerializedName("team")  val team:Team

)

data class InvitesSent (

    @SerializedName("inv") val  id:String,
    @SerializedName("participant") val  participant:User,
    @SerializedName("team") val team:Team
    )