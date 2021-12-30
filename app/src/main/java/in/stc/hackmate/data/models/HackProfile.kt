package `in`.stc.hackmate.data.models


import com.google.gson.annotations.SerializedName;
import java.io.Serializable

data class HackProfile (
    @SerializedName("_id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("venue") val venue: String,
    @SerializedName("start") val start: String,
    @SerializedName("end") val end: String,
    @SerializedName("poster") val poster: String,
    @SerializedName("min_team_size") val min: String,
    @SerializedName("max_team_size") val max: String,
    @SerializedName("mode_of_conduct") val mode: String,
    @SerializedName("prize_pool") val prize: String,
    @SerializedName("description") val description: String,
    @SerializedName("website") val website: String,
    @SerializedName("organsier_id") val oid: String,
    @SerializedName("__v") val v: String
    ) : Serializable

data class HackProfileResponse(
    @SerializedName("code")val code: Int?,
    @SerializedName("meta")val meta: String?,
    @SerializedName("data")val data: User?
)



