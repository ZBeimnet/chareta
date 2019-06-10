package com.example.chareta.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UsersWrapper (
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