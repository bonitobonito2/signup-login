package com.example.base.lays

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.base.R
import com.example.base.login
import com.example.base.profile
import com.example.base.signup
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text

class lay3:Fragment(R.layout.lay3) {
    lateinit var id:TextView
    lateinit var mail:TextView
    lateinit var logout:Button
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        id=view.findViewById(R.id.idd)
        mail=view.findViewById(R.id.mamail)
        logout=view.findViewById(R.id.logout)
      id.text=  "your ID:${FirebaseAuth.getInstance().currentUser?.uid}"
      mail.text="your email: ${FirebaseAuth.getInstance().currentUser?.email}"

        logout.setOnClickListener {
            val intent = Intent(activity, login::class.java)
            startActivity(intent)
            FirebaseAuth.getInstance().signOut()

        }

    }
}