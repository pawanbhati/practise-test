package com.shadi.com.practisetest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shadi.com.practisetest.Constants
import com.shadi.com.practisetest.models.ResultsItem


/**
 * This is class which wil have configurations related to database
 *
 * @author Pawan Bhati
 * @since 20-2-2021
 **/
@Database(entities = [ResultsItem::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDataDao(): UserDataDao

    companion object {
        private var INSTANCE: UserDatabase? = null
        fun getDatabase(context: Context): UserDatabase? {
            try {
                if (INSTANCE == null) {
                    synchronized(UserDatabase::class.java) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            UserDatabase::class.java, Constants.DB_NAME
                        ).build()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return INSTANCE
        }
    }
}