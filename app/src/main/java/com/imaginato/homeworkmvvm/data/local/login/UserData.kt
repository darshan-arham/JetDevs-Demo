package com.imaginato.homeworkmvvm.data.local.login

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserData")
data class UserData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val userID: String,
    val userName: String,
    val isDeleted: Boolean,
    val xAcc: String
)