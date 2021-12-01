package com.example.base.lays

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.base.R
import com.example.base.changePasswordKnown

class lay1: Fragment(R.layout.lay1) {
    lateinit var passchange:Button
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        passchange=view.findViewById(R.id.passchange)

        passchange.setOnClickListener {
            var intent=Intent(activity,changePasswordKnown::class.java)
            startActivity(intent)
        }
    }
}