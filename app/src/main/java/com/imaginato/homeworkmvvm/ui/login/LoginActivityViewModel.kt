package com.imaginato.homeworkmvvm.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.imaginato.homeworkmvvm.data.remote.login.Demo2DataRepository
import com.imaginato.homeworkmvvm.data.remote.login.LoginResponse
import com.imaginato.homeworkmvvm.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class LoginActivityViewModel(private val repository: Demo2DataRepository) : BaseViewModel() {

    fun getLoginResponse(name: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getLoginResponse(name, password)
        }
    }

    val loginResponse: LiveData<LoginResponse> get() = repository.response

}