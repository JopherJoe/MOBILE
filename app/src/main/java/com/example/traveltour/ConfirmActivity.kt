package com.example.traveltour

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ConfirmActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm)

        val departureTextView = findViewById<TextView>(R.id.departure)
        val returndateTextView = findViewById<TextView>(R.id.returndate)

        val confirmButton = findViewById<Button>(R.id.confirm)

        confirmButton.setOnClickListener {
            val departure = departureTextView.text.toString()
            val returndate = returndateTextView.text.toString()

            if (departure.isEmpty() || returndate.isEmpty()) {
                Toast.makeText(this@ConfirmActivity, "Please fill out the required fields", Toast.LENGTH_SHORT).show()
            } else {
                // Continue with making the network request to your server
                val client = OkHttpClient()
                val apiUrl = "http://192.168.0.10:5000/bookings/add"

                val confirmData = JSONObject()
                confirmData.put("departure_date", departure)
                confirmData.put("return_date", returndate)

                val requestBody = RequestBody.create(
                    MediaType.parse("application/json; charset=utf-8"),
                    confirmData.toString()
                )

                val request = Request.Builder()
                    .url(apiUrl)
                    .post(requestBody)
                    .addHeader("Content-Type", "application/json")
                    .build()

                client.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        runOnUiThread {
                            Toast.makeText(this@ConfirmActivity, "Failed to connect to the server", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onResponse(call: Call, response: Response) {
                        val responseCode = response.code()

                        runOnUiThread {
                            if (responseCode == 201) {
                                // Data confirmed and sent to the server successfully
                                Toast.makeText(this@ConfirmActivity, "Confirmed", Toast.LENGTH_SHORT).show()
                                // Optionally, navigate to another activity or perform other actions here.
                                val intent = Intent(this@ConfirmActivity, CategoriesActivity::class.java)
                                startActivity(intent)
                            } else {
                                // Handle other response codes here
                                Toast.makeText(this@ConfirmActivity, "Failed", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
            }
        }
    }
}
