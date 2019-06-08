package com.example.chareta.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UsersEmbedded (
    @SerializedName("_embedded")
    @Expose
    val embeddedUsers: UserList
) {

    data class UserList(
        @SerializedName("items")
        @Expose
        val allUsers: List<User>
    )

}