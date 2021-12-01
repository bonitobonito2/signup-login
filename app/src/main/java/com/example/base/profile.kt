package com.example.base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class profile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val menu= findViewById<BottomNavigationView>(R.id.menu2)
        val controller = findNavController(R.id.menu2Controller)
        val config=AppBarConfiguration(
            setOf(
                R.id.home,
                R.id.dashboard,
                R.id.settings

            )
        )
        setupActionBarWithNavController(controller,config)
        menu.setupWithNavController(controller)


    }


}