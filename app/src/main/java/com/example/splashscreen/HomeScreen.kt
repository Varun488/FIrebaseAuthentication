package com.example.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeScreen : AppCompatActivity() {

    lateinit var database: DatabaseReference
    lateinit var userRecyclerView: RecyclerView
    lateinit var userArrayList: ArrayList<NoteData>
    val mAuth = FirebaseAuth.getInstance()
    val mUser = mAuth.currentUser
    val arrayList = ArrayList<NoteData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        val Fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        Fab.setOnClickListener{
               val intent = Intent(this, AddNote::class.java )
               startActivity(intent)
        }
        userRecyclerView = findViewById(R.id.datalist)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)

        userArrayList = arrayListOf<NoteData>()
        getNoteData()

    }

    private fun getNoteData() {
        val uId = mUser?.uid.toString()
        database = FirebaseDatabase.getInstance().getReference("Notes").child(uId)

        database.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(userSnapshot in snapshot.children){
                        val note = userSnapshot.getValue(NoteData::class.java)
                        userArrayList.add(note!!)
                    }
                    userRecyclerView.adapter = MyAdapter(userArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}