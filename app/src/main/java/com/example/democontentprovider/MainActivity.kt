package com.example.democontentprovider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(
            applicationContext,
            SampleAppDatabase::class.java, "sample-database-name"
        ).build()

        lifecycleScope.launch {
            withContext(Dispatchers.IO){
                    for (j in 0..9999999)
                        db.userDao().insertAll(SampleUser("df$$j", "dfs+$j"))
            }
            withContext(Dispatchers.Main)
            {
                findViewById<ProgressBar>(R.id.progressDemo).visibility = View.GONE


            }
        }


    }

}