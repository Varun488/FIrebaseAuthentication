package com.example.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.logging.Handler

class SecondActivity : AppCompatActivity() {
    // create variable for database

    lateinit var Auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        Auth = FirebaseAuth.getInstance()
        val emailfield = findViewById<EditText>(R.id.etemail)
        val passwordfield = findViewById<EditText>(R.id.etpassword)
        val confPasswordfield = findViewById<EditText>(R.id.etconfpassword)
        val singupbtn = findViewById<Button>(R.id.button)
        val loginbtn = findViewById<TextView>(R.id.textView3)

        singupbtn.setOnClickListener {
            val email = emailfield.text.toString()
            val password = passwordfield.text.toString()
            val confpassword = confPasswordfield.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty() && confpassword.isNotEmpty()){
                if(password == confpassword){
                    Auth.createUserWithEmailAndPassword(email , password).addOnCompleteListener {
                        if(it.isSuccessful){
                            intent = Intent(this, SigninActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_LONG).show()
                        }
                    }
                }else{
                    Toast.makeText(this, "password and confirm password do not match", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this, "every field is required", Toast.LENGTH_LONG).show()
            }

        }

        loginbtn.setOnClickListener {
            intent = Intent(this, SigninActivity::class.java)
            startActivity(intent)
        }

    }
}