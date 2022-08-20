package com.example.democontentprovider

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DemoDao {
    @Insert
    fun insertAll( users: SampleUser)


    @Query("SELECT * FROM  ABC")
    fun selectAll(): Cursor
}