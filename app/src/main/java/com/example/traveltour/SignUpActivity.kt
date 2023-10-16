package com.example.traveltour

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_sign_up)

        val createAccount = findViewById<Button>(R.id.createaccount)
        val firstname = findViewById<EditText>(R.id.signup_firstname)
        val lastname = findViewById<EditText>(R.id.signup_lastname)
        val email = findViewById<EditText>(R.id.signup_emailaddress)
        val password = findViewById<EditText>(R.id.signup_password)
        val contact = findViewById<EditText>(R.id.signup_contactno)

        createAccount.setOnClickListener {
            val userfirstname = firstname.text.toString()
            val userlastname = lastname.text.toString()
            val useremail = email.text.toString()
            val userpassword = password.text.toString()
            val usercontact_no = contact.text.toString()

            if (userfirstname.isEmpty() || userlastname.isEmpty() || useremail.isEmpty() || userpassword.isEmpty() || usercontact_no.isEmpty()) {
                showToast("Please fill out all required fields.")
            } else {
                // Continue with making the network request
                val client = OkHttpClient()
                val apiUrl = "http://192.168.0.10:5000/users/sign-up"

                val json = JSONObject()
                json.put("firstname", userfirstname)
                json.put("lastname", userlastname)
                json.put("email", useremail)
                json.put("password", userpassword)
                json.put("contact_no", usercontact_no)

                val requestBody = RequestBody.create(
                    MediaType.parse("application/json"),
                    json.toString()
                )

                val request = Request.Builder()
                    .url(apiUrl)
                    .post(requestBody)
                    .addHeader("Content-Type", "application/json")
                    .build()

                client.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        Log.e("MainActivity2", "Network request failed", e)
                        showToast("Failed to connect to the server")
                    }

                    override fun onResponse(call: Call, response: Response) {
                        val responseBody = response.body()?.string()
                        Log.d("MainActivity2", "Response code: ${response.code()}")
                        Log.d("MainActivity2", "Response body: $responseBody")
                        Log.d("MainActivity2", "password: $userpassword")

                        runOnUiThread {
                            if (response.isSuccessful) {
                                when (response.code()) {
                                    201 -> {
                                        showToast("User created successfully: $responseBody")
                                        val intent = Intent(this@SignUpActivity, CreateLoginActivity::class.java)
                                        startActivity(intent)
                                    }
                                    400 -> {
                                        showToast("Bad request: $responseBody")
                                    }
                                    500 -> {
                                        showToast("Internal server error: $responseBody")
                                    }
                                    else -> {
                                        showToast("Unexpected error occurred: $responseBody")
                                    }
                                }
                            } else {
                                showToast("Request not successful")
                            }
                        }
                    }
                })
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this@SignUpActivity, message, Toast.LENGTH_SHORT).show()
    }
}
