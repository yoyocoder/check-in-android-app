package com.yoyocoder.checkin

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.yoyocoder.checkin.databinding.ActivityMainBinding
import com.yoyocoder.checkin.main.CheckInEntryListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, CheckInEntryListFragment())
            .addToBackStack(null)
            .commit()
    }
}