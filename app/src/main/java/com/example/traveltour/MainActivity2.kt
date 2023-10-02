package com.example.traveltour

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val createAccount = findViewById<Button>(R.id.createaccount)
        val firstname = findViewById<EditText>(R.id.signup_firstname)
        val lastname = findViewById<EditText>(R.id.signup_lastname)
        val email = findViewById<EditText>(R.id.signup_emailaddress)
        val password = findViewById<EditText>(R.id.signup_password)
        val contact = findViewById<EditText>(R.id.signup_contactno)


        createAccount.setOnClickListener(View.OnClickListener {
            val firstname = firstname.text.toString()
            val lastname = lastname.text.toString()
            val email = email.text.toString()
            val password = password.text.toString()
            val contact = contact.text.toString()

            if(firstname.isEmpty() || lastname.isEmpty() || email.isEmpty() || password.isEmpty() || contact.isEmpty()){
               Toast.makeText(this@MainActivity2, "Please fill out all required fields.", Toast.LENGTH_SHORT).show()
            }else {

                val intent = Intent(this@MainActivity2, MainActivity::class.java)
                startActivity((intent))
                Toast.makeText(this@MainActivity2, "You've successfully created your account.", Toast.LENGTH_SHORT).show()
            }
        })

    }
}
