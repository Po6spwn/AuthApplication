package com.example.myapplication.util

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/* Класс для правильного хранения результата запроса*/
data class User (
    @SerializedName("access_token")
    @Expose
    var accessToken: String,

    @SerializedName("nickname")
    @Expose
    var nickName: String,

    @SerializedName("avatar")
    @Expose
    var avatar: Avatar
)

data class Avatar(
    @SerializedName("thumb")
    @Expose
    var thumb: String
)