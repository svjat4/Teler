package com.kampus.teler.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kampus.teler.R

class HomeActivity : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HomeFragment())
                .commitNow()
        }
    }

}