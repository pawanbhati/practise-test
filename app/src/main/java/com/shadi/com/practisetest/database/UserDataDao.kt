package com.shadi.com.practisetest.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.shadi.com.practisetest.models.ResultsItem

/**
 * This interface will have all the queries declared here.
 *
 * @author Pawan Bhati
 * @since 20-2-2021
 **/
@Dao
interface UserDataDao {

    /**Method is used to insert data in database
     * @param item: single user/record received from api
     * */
    @Insert
    fun setUserData(item: ResultsItem)

    /**Method is used to retrieve data from database
     * @return : list of users/records
     * **/
    @Query("SELECT * from user_table ORDER BY db_id ASC")
    fun getAllUsers(): LiveData<List<ResultsItem>>

    /*Method is used to delete data from database*/
    @Query("DELETE from user_table")
    fun deleteAllRecords()

    /**Method is used to update data from database
    * @param value : boolean flag used to update invitation status (decline/accept)
     * @param db_id : int, auto-increment database row id
    **/
    @Query("UPDATE user_table SET isInvitationAccepted=:value WHERE db_id =:id")
    fun updateInvitationStatus(value: Boolean, id: Int)
}