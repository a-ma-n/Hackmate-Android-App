package `in`.stc.hackmate.data.models

import com.google.gson.annotations.SerializedName

data class SkillsResponse(
    @SerializedName("code")val code: Int?,
    @SerializedName("meta")val meta: String?,
    @SerializedName("data")val skills: List<String>
)

public class Skills(
    @SerializedName("code")val id: String,
    @SerializedName("skill")val skill: String,
    @SerializedName("participant_id")val p_id: String,
    @SerializedName("__v")val v: Int
    )

