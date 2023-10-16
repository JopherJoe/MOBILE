package com.example.traveltour

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class CreateLoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_login)

        val textView2 = findViewById<TextView>(R.id.signup)
        textView2.setOnClickListener {
            val intent = Intent(this@CreateLoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }

        val logemail = findViewById<EditText>(R.id.login_email)
        val logpass = findViewById<EditText>(R.id.login_password)

        val log = findViewById<Button>(R.id.login)

        log.setOnClickListener {
            val logemailText = logemail.text.toString()
            val logpassText = logpass.text.toString()

            if (logemailText.isEmpty() || logpassText.isEmpty()) {
                Toast.makeText(this@CreateLoginActivity, "Please fill out the required fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val client = OkHttpClient()
            val apiUrl = "http://192.168.0.10:5000/users/login"

            val json = JSONObject()
            json.put("email", logemailText)
            json.put("password", logpassText)

            val requestBody = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                json.toString()
            )

            val request = Request.Builder()
                .url(apiUrl)
                .post(requestBody)
                .addHeader("Content-Type", "application/json")
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    runOnUiThread {
                        Toast.makeText(this@CreateLoginActivity, "Failed to connect to the server", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    val responseCode = response.code()

                    if (responseCode == 200) {
                        // Login successful
                        runOnUiThread {
                            Toast.makeText(this@CreateLoginActivity, "Log In successfully", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@CreateLoginActivity, HomeActivity::class.java)
                            startActivity(intent)
                        }
                    } else if (responseCode == 401) {
                        // Unauthorized: Email does not match
                        runOnUiThread {
                            Toast.makeText(this@CreateLoginActivity, "Email does not match", Toast.LENGTH_SHORT).show()
                        }
                    } else if (responseCode == 402) {
                        // Unauthorized: Password does not match
                        runOnUiThread {
                            Toast.makeText(this@CreateLoginActivity, "Password does not match", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        // Handle other response codes here
                        runOnUiThread {
                            val responseBody = response.body()?.string()
                            Toast.makeText(this@CreateLoginActivity, "Login failed: $responseBody", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
    }
}
