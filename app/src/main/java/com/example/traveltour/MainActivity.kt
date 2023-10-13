package com.example.traveltour


import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity(){
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

            setContentView(R.layout.activity_main)


            val textView2 = findViewById<TextView>(R.id.signup)
            textView2.setOnClickListener {
                val intent = Intent (this@MainActivity, MainActivity2::class.java)
                startActivity(intent)

        }
        val logemail = findViewById<EditText>(R.id.login_email)
        val logpass = findViewById<EditText>(R.id.login_password)

        val log = findViewById<Button>(R.id.login)
        log.setOnClickListener{
            val logemail = logemail.text.toString()
            val logpass = logpass.text.toString()

            if(logemail.isEmpty() || logpass.isEmpty()){
                Toast.makeText(this@MainActivity, "Please Fill out the required fields", Toast.LENGTH_SHORT).show()
            }else {
                val intent = Intent (this@MainActivity, HomeActivity::class.java)
                startActivity(intent)
                Toast.makeText(this@MainActivity, "Log In successfully", Toast.LENGTH_SHORT).show()
            }


        }
    }
}