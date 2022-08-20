package com.example.democontentprovider

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [SampleUser::class], version = 1)
abstract class SampleAppDatabase: RoomDatabase() {




    companion object {
        var sInstance: SampleAppDatabase? = null

        @Synchronized
        open fun getInstance(context: Context): SampleAppDatabase? {
            if (sInstance == null) {
                sInstance = Room
                    .databaseBuilder(
                        context.getApplicationContext(),
                        SampleAppDatabase::class.java,
                        "ex"
                    )
                    .build()
            }
            return sInstance
        }
    }


    abstract fun userDao(): DemoDao
}