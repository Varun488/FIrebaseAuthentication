package com.example.splashscreen

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.firestore
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class AddNote : AppCompatActivity() {

    val db = Firebase.firestore
    val mAuth = FirebaseAuth.getInstance()
    val mUser = mAuth.currentUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        val saveBtn = findViewById<Button>(R.id.addBtn)
        val titleField = findViewById<EditText>(R.id.editTextText)
        val contentfield = findViewById<EditText>(R.id.edit_text)
        val uId = mUser?.uid.toString()



        saveBtn.setOnClickListener {

            val date: String = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault()).format(Date())
            val title = titleField.text.toString()
            val content = contentfield.text.toString()
            val Note = hashMapOf(
                "Title" to title,
                "Content" to content,
                "Date" to date,
            )

            FirebaseDatabase.getInstance().reference.child("Notes").child(uId).child(date).setValue(Note)
                .addOnSuccessListener {
                    titleField.text.clear()
                    contentfield.text.clear()

                    Toast.makeText(this,"Data inserted successfully",Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()
                }
        }

    }
}