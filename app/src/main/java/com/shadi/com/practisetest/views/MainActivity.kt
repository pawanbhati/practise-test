package com.shadi.com.practisetest.views

import android.content.pm.FeatureInfo
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.shadi.com.practisetest.Constants
import com.shadi.com.practisetest.R
import com.shadi.com.practisetest.adapters.UserListingRVAdapter
import com.shadi.com.practisetest.models.ResultsItem
import com.shadi.com.practisetest.models.UserData
import com.shadi.com.practisetest.viewmodels.UserListViewModel
import kotlinx.android.synthetic.main.activity_main.*

/**
 * This is the only activity in this app which will be visible to user once user tap the app icon
 *
 * @author Pawan Bhati
 * @since 20-2-2021
**/
class MainActivity : AppCompatActivity(), UserListingRVAdapter.ItemClickListener {

    //This is instance of adapter for recyclervew
    var userListingRVAdapter: UserListingRVAdapter? = null
    //This is instance of viewmodel use in MainActivity
    lateinit var userListViewModel: UserListViewModel


    /**
     * This method is overridden method to manage android activity lifecycle
     **/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)

        userListViewModel = ViewModelProviders.of(this).get(UserListViewModel::class.java)

        //get data from DB and load recyclerview
        userListViewModel.getUsersFromDB()?.observe(this, Observer<List<ResultsItem>> {
            userListingRVAdapter?.setResult(it.reversed())
        })

        //get records from API
        userListViewModel.userLiveData?.observe(this, object : Observer<UserData> {
            override fun onChanged(userData: UserData?) {
                //insert data into database
                insertDataInDB(userData?.results)
            }
        })

        //instantiating adapter here..
        userListingRVAdapter = UserListingRVAdapter(this, this)

        //applying changes to recyclerview
        rvActivityMainUsers?.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userListingRVAdapter
        }

        //calling api through retrofit
        userListViewModel.getUsersFromApi(Constants.TOTAL_RECORDS)

    }

    /**
     * Method to store data in database
     * @param resultsFromAPI:  list of user retrieved from api
     * */
    fun insertDataInDB(resultsFromAPI: List<ResultsItem?>?) {

        //this flag is used to make sure that below obsever code runs only once.
        var isDataRetrieved = false
        userListViewModel.getUsersFromDB()?.observe(this, Observer { resultItemList ->
            if (isDataRetrieved) {
                return@Observer
            }
            isDataRetrieved = true

            //this will help is getting only those user which we retrieved from api and does not exists in DB
            val difference = resultsFromAPI?.minus(resultItemList)

            //below code is used to insert values to database
            difference?.map {
                userListViewModel.setUsers(it)
            }
        })
    }


    /**
     * This is a callback which will get called once user clicks on decline view from recyclerview
     * @param resultsItem : listitem received from list passed in recyclerview according to position
     **/
    override fun onDeclinedClick(resultsItem: ResultsItem?) {
        resultsItem?.let {
            userListViewModel.updateUsers(it.isInvitationAccepted!!, it.db_id)
        }
    }

    /**
     * this is a callback which will get called once user clicks on accept view from recyclerview
     * @param resultsItem : listitem received from list passed in recyclerview according to position
     **/
    override fun onAcceptedClick(resultsItem: ResultsItem?) {
        resultsItem?.let {
            userListViewModel.updateUsers(it.isInvitationAccepted!!, it.db_id)
        }
    }


}