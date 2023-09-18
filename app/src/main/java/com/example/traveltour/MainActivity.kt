package com.example.traveltour

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.traveltour.ui.theme.TRAVELTOURTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            setContentView(R.layout.activity_main)
            val nextActivityButton = findViewById<Button>(R.id.btnNext)
            val nextActivityButton2 = findViewById<Button>(R.id.btnSignUp)

            nextActivityButton.setOnClickListener{
                val intent = Intent(this, MainActivity3::class.java)
                startActivity(intent)
            }
            nextActivityButton2.setOnClickListener{
                val intent = Intent (this, MainActivity2::class.java)
                startActivity(intent)
            }
        }
    }
}
