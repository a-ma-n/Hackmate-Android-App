package `in`.stc.hackmate.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

//todo check this model
data class Team(
    @SerializedName("name") val name: String,
    @SerializedName("_id") val id: String,
    @SerializedName("admin_id") val a_id:String,
    @SerializedName("team_code") val team_code:String,
    @SerializedName("members") val members: List<Member>?,
    @SerializedName("hackName") val hackName:String,
    @SerializedName("ptSkill") val participants: List<Pts>?,
    ): Serializable
data class Pts (
        @SerializedName("participant") val pt :User,
        @SerializedName("skills")val skills:Skills
            )
data class Member (
    @SerializedName("_id") val m_id:String,
    @SerializedName("uid") val u_id:String
    )

data class TeamResponse(
    @SerializedName("code")val code: Int?,
    @SerializedName("meta")val meta: String?,
    @SerializedName("data")val data: Team?
)