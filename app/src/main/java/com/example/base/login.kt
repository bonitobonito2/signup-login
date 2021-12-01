package com.example.base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class login : AppCompatActivity() {
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var loginbtn: Button
    lateinit var signupBtn: Button
    lateinit var changePassBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // იმის გამო რომ ეს აპლიკაცია 2 დღის წინ გავაკეთე და არ მქონდა ვერიფიკაცია, შეიძლება ვინმე შესული იყოს უკვე და არ ქონდეს ფერიფიკაცია, ამიტომ ვამოწმებ რომ თუ არ აქვს გამოაგდოს
        if(FirebaseAuth.getInstance().currentUser !=null && FirebaseAuth.getInstance().currentUser?.isEmailVerified==true){
            goToProfile()
        }else{
            FirebaseAuth.getInstance().signOut()
        }
        setContentView(R.layout.login)
        init()
        registarListener()
    }
    private fun init(){
        email=findViewById(R.id.editTextTextEmailAddress)
        password=findViewById(R.id.editTextTextPassword)
        loginbtn=findViewById(R.id.loginBtn)
        signupBtn=findViewById(R.id.signupbtn)
        changePassBtn=findViewById(R.id.resetPassBtwn)

    }
    private fun registarListener(){
        signupBtn.setOnClickListener{
            val intent = Intent(this,signup::class.java)
            startActivity(intent)
        }

        changePassBtn.setOnClickListener {
            val intent= Intent(this,resetPassword::class.java)
            startActivity(intent)
        }

        loginbtn.setOnClickListener {
            email=findViewById(R.id.editTextTextEmailAddress)
            password=findViewById(R.id.editTextTextPassword)
            val emailValue=email.text.toString()
            val passwordValue=password.text.toString()
              //ვამოწმებ ცარიელია თუ არა პაროლიდა მეილი
            if(emailValue.isEmpty() || passwordValue.isEmpty()){
                Toast.makeText(this, "fill the email or password tasks", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(emailValue, passwordValue)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            //თუ არსებობს იუზერი, ვამოწმებ აქვს თუ არა გავლილი ვერიფიკაცია მის მეილს
                            if(FirebaseAuth.getInstance().currentUser?.isEmailVerified==true) {
                                goToProfile()
                            }else{
                                // თუ არ აქვს გავლილი ვუგზავნი ხელახლა
                                FirebaseAuth.getInstance().currentUser?.sendEmailVerification()
                                Toast.makeText(this,"we sent u email verification text on ${FirebaseAuth.getInstance().currentUser?.email}, please check",Toast.LENGTH_LONG).show()
                            }
                        } else {
                            Toast.makeText(this, "wrong password or E-mail", Toast.LENGTH_SHORT)
                                .show()
                        }


            }

            }
    }

    private fun goToProfile(){
        val intent=Intent(this,profile::class.java)
        startActivity(intent)
        finish()
    }

}