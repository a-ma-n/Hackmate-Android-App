package `in`.stc.hackmate.data.models

import com.google.gson.annotations.SerializedName


data class Project (
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("code") val github: String,
    @SerializedName("design") val design: String,
    @SerializedName("implementation") val implementation: String
){
    @SerializedName("_id")
    var id: String = ""
}

data class ProjectResponse(
    @SerializedName("code")val code: Int?,
    @SerializedName("meta")val meta: String?,
    @SerializedName("data")val data: Project?
)