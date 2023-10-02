package com.example.traveltour

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

            setContentView(R.layout.activity_main);


            val textView2 = findViewById<TextView>(R.id.signup)
            textView2.setOnClickListener {
                val intent = Intent (this@MainActivity, MainActivity2::class.java)
                startActivity(intent)


        }
    }
}