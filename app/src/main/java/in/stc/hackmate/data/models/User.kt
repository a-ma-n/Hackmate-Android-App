package `in`.stc.hackmate.data.models

import com.google.gson.annotations.SerializedName
import javax.annotation.Nullable


data class User(
    @Nullable
    @SerializedName("id") val id: String,
    @SerializedName("bio") val bio: String,
    @SerializedName("college") val college: String,
    @SerializedName("github") val github: String,
    @SerializedName("graduation_year") val graduation_year: Int,
    @SerializedName("linkedIn") val linkedIn: String,
    @SerializedName("name") val name: String,
    @SerializedName("photo") val photo: String,
    @SerializedName("username") val username: String,
    @SerializedName("website") val website: String?,
    @SerializedName("email") val mail: String?

)

data class UserResponse(
    @SerializedName("code")val code: Int?,
    @SerializedName("meta")val meta: String?,
    @SerializedName("data")val data: User?
    )

