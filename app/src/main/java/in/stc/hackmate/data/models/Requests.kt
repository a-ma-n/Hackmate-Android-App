package `in`.stc.hackmate.data.models

import com.google.gson.annotations.SerializedName

//todo : write the request data class, fix this
data class Requests (
    @SerializedName("code")val code: Int?,
    @SerializedName("meta")val meta: String?,
    @SerializedName("data")val data: Requests?
)
data class RequestsResponse (
    @SerializedName("code")val code: Int?,
    @SerializedName("meta")val meta: String?,
    @SerializedName("data")val data: Requests?
        )
