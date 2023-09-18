package com.example.traveltour

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity3 : ComponentActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            setContentView(R.layout.activity_main3)
            val cancelButton2 = findViewById<Button>(R.id.btnCancel2)

            cancelButton2.setOnClickListener {
                finish()
            }
        }
    }
}