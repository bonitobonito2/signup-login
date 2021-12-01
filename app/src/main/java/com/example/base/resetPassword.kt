package com.example.base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class resetPassword : AppCompatActivity() {

   lateinit var changepassgraph:EditText
   lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
         changepassgraph=findViewById(R.id.changepas)
        button=findViewById(R.id.changepasButton)
        resetPass()
    }

private fun resetPass(){
  button.setOnClickListener {
      changepassgraph=findViewById(R.id.changepas)
      var email=changepassgraph.text.toString()
      if(email.isEmpty()){
          Toast.makeText(this, "email is empty", Toast.LENGTH_SHORT).show()
          return@setOnClickListener
      }
      FirebaseAuth.getInstance()
          .sendPasswordResetEmail(email)
          .addOnCompleteListener { task->
              if(task.isSuccessful){
                  Toast.makeText(this,"check gmail, we sent u sms",Toast.LENGTH_LONG).show()
              }else{
                  Toast.makeText(this, "eror", Toast.LENGTH_SHORT).show()
              }
          }

  }

}

}