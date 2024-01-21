package com.example.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException

class SigninActivity : AppCompatActivity() {

    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        val emailField = findViewById<EditText>(R.id.emailL)
        val passwordField = findViewById<EditText>(R.id.etPasswordL)
        val loginBtn = findViewById<Button>(R.id.btnLogin)
        auth = FirebaseAuth.getInstance()

        loginBtn.setOnClickListener {
            val email = emailField.text.toString()
            val pasword = passwordField.text.toString()
            if(email.isNotEmpty() && pasword.isNotEmpty()){
                auth.signInWithEmailAndPassword(email,pasword).addOnCompleteListener() {
                    if(it.isSuccessful){
                         intent = Intent(this, HomeScreen::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this,it.exception.toString(),Toast.LENGTH_LONG).show()
                    }
                }
            }else{
                Toast.makeText(this, "Please fill both fields", Toast.LENGTH_LONG).show()
            }
        }


    }
}