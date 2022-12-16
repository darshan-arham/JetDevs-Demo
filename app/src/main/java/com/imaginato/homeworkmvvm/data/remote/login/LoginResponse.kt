package com.imaginato.homeworkmvvm.data.remote.login

import com.google.gson.annotations.SerializedName

data class LoginResponse constructor(
    @SerializedName("errorCode") var errorCode: String?,
    @SerializedName("errorMessage") var errorMessage: String?,
    @SerializedName("data") var data: UserProfile?
)

data class UserProfile(
    @SerializedName("userId") var userID: String?,
    @SerializedName("userName") var userName: String?,
    @SerializedName("isDeleted") var isDeleted: Boolean?,
)
