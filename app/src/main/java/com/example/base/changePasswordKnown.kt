package com.example.base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class changePasswordKnown : AppCompatActivity() {
    lateinit var changepass:EditText
    lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password_known)
        changepass=findViewById(R.id.changelan)
        button=findViewById(R.id.changelan1)
        button.setOnClickListener {
           val pas=changepass.text.toString()
            if(pas.length<9){
                Toast.makeText(this,"password letters are less then 9",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance()
                .currentUser?.updatePassword(pas)
                ?.addOnCompleteListener { task->
                    if(task.isSuccessful ){
                        var back = Intent(this,profile::class.java)
                        startActivity(back)
                    }else{
                        Toast.makeText(this,"eror",Toast.LENGTH_LONG).show()
                    }

                }
        }
    }
}