package `in`.stc.hackmate.data.models

import com.google.gson.annotations.SerializedName

data class HackList (
    @SerializedName("final") val hack: List<HackProfile>,
    @SerializedName("length") val length: Int
    )

data class HackListResponse(
    @SerializedName("code")val code: Int?,
    @SerializedName("meta")val meta: String?,
    @SerializedName("data")val data: User?
)

