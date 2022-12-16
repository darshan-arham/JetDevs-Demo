package com.imaginato.homeworkmvvm.ui.base

import android.app.Application
import com.imaginato.homeworkmvvm.data.local.login.UserDatabase
import com.imaginato.homeworkmvvm.data.remote.login.Demo2DataRepository
import com.imaginato.homeworkmvvm.domain.*
import com.imaginato.homeworkmvvm.ui.login.RetrofitAPi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class IApp : Application() {

    lateinit var demo2Repository: Demo2DataRepository

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        val api = RetrofitAPi.getInstance()
        val database = UserDatabase.getDatabase(applicationContext)
        demo2Repository = Demo2DataRepository(api, database)

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@IApp)
            modules(
                databaseModule, netModules, apiModules, repositoryModules, viewModelModules
            )
        }
    }
}