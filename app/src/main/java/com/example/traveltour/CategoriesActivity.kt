package com.example.traveltour

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class CategoriesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        val viewboracay = findViewById<Button>(R.id.view_boracay)
        viewboracay.setOnClickListener {
            val intent = Intent (this@CategoriesActivity, BoracayPackActivity::class.java)
            startActivity(intent)
        }
    }
}