package com.imaginato.homeworkmvvm.data.remote.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.imaginato.homeworkmvvm.data.local.login.UserData
import com.imaginato.homeworkmvvm.data.local.login.UserDatabase

class Demo2DataRepository constructor(
    private val api: Demo2Api,
    private val database: UserDatabase
) {
    companion object {
        private const val IMSI = 357175048449937
        private const val IMEI = 510110406068589
    }

    private val mutableResponse = MutableLiveData<LoginResponse>()

    val response: LiveData<LoginResponse>
        get() = mutableResponse

    suspend fun getLoginResponse(userName: String, password: String) {
        val result = api.login(IMSI, IMEI, userName, password)
        if (result.body() != null) {
            mutableResponse.postValue(result.body())
            if (result.body()?.errorCode.equals("00")) {
                database.userDao().addUserData(
                    UserData(
                        id = 0,
                        userID = result.body()?.data?.userID!!,
                        userName = result.body()?.data?.userName!!,
                        isDeleted = result.body()?.data?.isDeleted!!,
                        xAcc = result.headers().get("X-ACC")!!,
                    )
                )
            }
        }

    }
}