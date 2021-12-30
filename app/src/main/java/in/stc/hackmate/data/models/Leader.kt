package `in`.stc.hackmate.data.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class Leader (
    @SerializedName("name") var name: String,

    @SerializedName("photo") var photo: String,

    @SerializedName("_id") var id: String? = null
)