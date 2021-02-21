package com.shadi.com.practisetest.database

import android.app.Application
import com.shadi.com.practisetest.models.ResultsItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

/**
 * This is a repository class which will be responsible for all the database fetching, updating, inserting
 *
 * @author Pawan Bhati
 * @since 20-2-2021
 **/
class UserRepository(application: Application) : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
    private var userDataDao: UserDataDao? = null

    init {
        val db = UserDatabase.getDatabase(application)
        userDataDao = db?.userDataDao()
    }

    fun getUsers() = userDataDao?.getAllUsers()
    fun insertUser(resultsItem: ResultsItem) {
        launch { setUserBG(resultsItem) }
    }

    fun updateUserInvitationStatus(value: Boolean, id: Int) {
        launch {
            updateUserData(value, id)
        }

    }

    private suspend fun updateUserData(value: Boolean, id: Int) {
        withContext(Dispatchers.IO) {
            userDataDao?.updateInvitationStatus(value, id)
        }
    }

    private suspend fun setUserBG(resultsItem: ResultsItem) {
        withContext(Dispatchers.IO) {
            userDataDao?.setUserData(resultsItem)
        }
    }

}