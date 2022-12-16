package com.imaginato.homeworkmvvm.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.imaginato.homeworkmvvm.data.remote.login.Demo2DataRepository

class LoginViewModelFactory(private val repository: Demo2DataRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return  LoginActivityViewModel(repository) as T
    }
}