package com.example.traveltour

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import android.widget.TextView
import android.widget.Button
import android.widget.Toast
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class BoracayPackActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boracay_pack)

        val destinationTextView = findViewById<TextView>(R.id.destination)
        val noOfPaxTextView = findViewById<TextView>(R.id.no_of_pax)
        val nightsTextView = findViewById<TextView>(R.id.nights)
        val daysTextView = findViewById<TextView>(R.id.days)
        val addonsTextView = findViewById<TextView>(R.id.add_ons)
        val priceTextView = findViewById<TextView>(R.id.price)

        val bookButton = findViewById<Button>(R.id.booknow)

        bookButton.setOnClickListener {
            val destination = destinationTextView.text.toString()
            val noOfPax = noOfPaxTextView.text.toString()
            val nights = nightsTextView.text.toString()
            val days = daysTextView.text.toString()
            val addons = addonsTextView.text.toString()
            val price = priceTextView.text.toString()

            if (destination.isEmpty() || noOfPax.isEmpty()) {
                Toast.makeText(this@BoracayPackActivity, "Please fill out the required fields", Toast.LENGTH_SHORT).show()
            } else {
                // Continue with making the network request to your server
                val client = OkHttpClient()
                val apiUrl = "http://192.168.0.10:5000/packages/add"

                val bookingData = JSONObject()
                bookingData.put("destination", destination)
                bookingData.put("no_of_pax", noOfPax.toInt())
                bookingData.put("nights", nights.toInt())
                bookingData.put("days", days.toInt())
                bookingData.put("add_ons", addons)
                bookingData.put("price", price.toDouble())

                val requestBody = RequestBody.create(
                    MediaType.parse("application/json; charset=utf-8"),
                    bookingData.toString()
                )

                val request = Request.Builder()
                    .url(apiUrl)
                    .post(requestBody)
                    .addHeader("Content-Type", "application/json")
                    .build()

                client.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        runOnUiThread {
                            Toast.makeText(this@BoracayPackActivity, "Failed to connect to the server", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onResponse(call: Call, response: Response) {
                        val responseCode = response.code()

                        runOnUiThread {
                            if (responseCode == 201) {
                                // Booking successful, handle accordingly
                                Toast.makeText(this@BoracayPackActivity, "Booking successful", Toast.LENGTH_SHORT).show()

                                val intent = Intent(this@BoracayPackActivity, ConfirmActivity::class.java)
                                startActivity(intent)
                                // Optionally, you can navigate to another activity or perform other actions here.
                            } else {
                                // Handle other response codes here
                                Toast.makeText(this@BoracayPackActivity, "Booking failed", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
            }
        }
    }
}
