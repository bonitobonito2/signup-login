package com.example.base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlin.math.sign

class signup : AppCompatActivity() {
    lateinit var email:EditText
    lateinit var password:EditText
    lateinit var signup:Button
    lateinit var repeatpassword:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseAuth.getInstance().signOut()
        setContentView(R.layout.activity_signup)

        init()
        registarListener()


    }
    private fun init(){
        repeatpassword=findViewById(R.id.passwordIn2)
        email=findViewById(R.id.emailin)
        password=findViewById(R.id.passwordIn)
        signup=findViewById(R.id.signup)

    }

    private fun registarListener() {
    var emailValue = email.text.toString()
    var passwordValue = password.text.toString()


    signup.setOnClickListener {
         var emailValue = email.text.toString()
         var passwordValue = password.text.toString()
         var repeatpassword=repeatpassword.text.toString()
        //ვამოწმებ ემაილის და პაროლის სიცარიელეს
        if (emailValue.isEmpty() || passwordValue.isEmpty()  ) {

            Toast.makeText(this, "fill password and email tasks", Toast.LENGTH_SHORT).show()

            return@setOnClickListener
        }
        //ვამოწმებ ემაილი ვალიდურია თუ არა
        if(!emailValue.contains("@") || !emailValue.contains(".") || !emailValue.contains("mail")){
            Toast.makeText(this, "invalid email", Toast.LENGTH_SHORT).show()
            return@setOnClickListener
        }
        //ვამოწმებ განმეორებითი პაროლი ემთხვევა თუ არა პირველს
        if(repeatpassword!=passwordValue){
            Toast.makeText(this, "first and second password arenot same", Toast.LENGTH_SHORT).show()
            return@setOnClickListener
        }
        //ქაუნთერი რომელიც საბოლოოდ გამაგებინებს არის თუ არა მთლიან პაროლის სტრინგში რიცხვი და თუ არ არის ამის საშვუალებით გამომაქვს ინფორმაცია
        var counter1=0
        //ვამოწმებ პაროლში არის თუ არა რიცხვი
         for(i in passwordValue){
            var  counter = passwordValue.length
             counter1++
            //თითვეულ ასოს ვამოწმებ ლუპში თუ არის რიცხვი
             if(i.isDigit() && passwordValue.length>9){

                 FirebaseAuth.getInstance()

                     .createUserWithEmailAndPassword(emailValue,passwordValue)
                     .addOnCompleteListener { task->
                         if(task.isSuccessful){
                             //თუ ემაილი და პაროლი ვალიდურია ვუგზავნი მოხმარებელს ემაილზე ვერიფიკაციას
                             FirebaseAuth.getInstance().currentUser?.sendEmailVerification()?.addOnCompleteListener {
                                 Toast.makeText(this,"we sent u email verification text on ${FirebaseAuth.getInstance().currentUser?.email}, please check",Toast.LENGTH_LONG).show()
                                 FirebaseAuth.getInstance().signOut()
                                 finish()


                             }

                         }else{
                             Toast.makeText(this, "gmail is already in usage ", Toast.LENGTH_SHORT).show()
                             finish()
                         }

             }

              // როდესაც ქაუნთერ ერთი უტოლდება პაროლის სიგრძეს ლუპში ესეგი სტრინგში არ არსებობს რიცხვი
         }else if(counter==counter1){
                 android.widget.Toast.makeText(this, "there isnot number in your password or length is less than 9", android.widget.Toast.LENGTH_SHORT).show()
                 return@setOnClickListener
             }


                }
        }
    }


    }
//}