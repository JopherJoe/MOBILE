package com.example.traveltour

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val book = findViewById<Button>(R.id.book)
        book.setOnClickListener {
            intent = Intent (this@HomeActivity, CategoriesActivity::class.java)
            startActivity(intent)
        }
    }
}