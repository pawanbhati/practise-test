package com.shadi.com.practisetest.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.shadi.com.practisetest.database.UserRepository
import com.shadi.com.practisetest.models.ResultsItem
import com.shadi.com.practisetest.models.UserData
import com.shadi.com.practisetest.network.RestAPI


/**
 * This is view model developed to contain all the business logic and api calls for MainActivity
 *
 * @author Pawan Bhati
 * @since 20-2-2021
 **/
class UserListViewModel(application: Application) : AndroidViewModel(application) {

    var userLiveData: MutableLiveData<UserData>? = null
    var restAPI: RestAPI? = null
    var repository: UserRepository


    /* Method is used to retrieve data from database */
    fun getUsersFromDB() = repository.getUsers()

    /* Method is used to insert data into database */
    fun setUsers(resultsItem: ResultsItem?) {
        resultsItem?.let {
            repository.insertUser(it)
        }
    }

    /* Method is used to update invitation status to a sinle record in database */
    fun updateUsers(value: Boolean, id: Int) {
        repository.updateUserInvitationStatus(value, id)
    }

    init {
        restAPI = RestAPI()
        repository = UserRepository(application)
        userLiveData = restAPI?.mutableUserData
    }


    /* Method is used to retrieve data from server */
    fun getUsersFromApi(records: String) {
        restAPI?.getUsers(records)
    }




}