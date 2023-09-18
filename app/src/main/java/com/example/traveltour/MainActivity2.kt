package com.example.traveltour

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent



class MainActivity2 : ComponentActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            setContentView(R.layout.activity_main2)
            val cancelButton = findViewById<Button>(R.id.btnCancel)
            val signUpButton = findViewById<Button>(R.id.btnSignUp2)

            cancelButton.setOnClickListener{
                finish()
            }
            signUpButton.setOnClickListener{
                val intent = Intent (this, MainActivity3::class.java)
                startActivity(intent)
            }
        }
    }
}