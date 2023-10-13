package com.example.traveltour

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



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
            val userfirstname = firstname.text.toString()
            val userlastname = lastname.text.toString()
            val useremail = email.text.toString()
            val userpassword = password.text.toString()
            val usercontact_no = contact.text.toString()

            if(userfirstname.isEmpty() || userlastname.isEmpty() || useremail.isEmpty() || userpassword.isEmpty() || usercontact_no.isEmpty()){
                Toast.makeText(this@MainActivity2, "Please fill out all required fields.", Toast.LENGTH_SHORT).show()
            }else {

                val userSignUpService = NetworkClient.retrofit.create(UserSignUp::class.java)

                // calling them on my ApiService.kt
                val signUpData = SignUp (userfirstname, userlastname, useremail, userpassword, usercontact_no)

                //Send the sign-up request to the Api
                val call: Call<SignUpResponse> = userSignUpService.signUp(signUpData)
                call.enqueue(object: Callback<SignUpResponse> {
                    override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                        if(response.isSuccessful){
                            //The Sign-Up was successful
                            val intent = Intent(this@MainActivity2, MainActivity::class.java)
                            startActivity(intent)
                            Toast.makeText(this@MainActivity2, "You've successfully created your account.", Toast.LENGTH_SHORT).show()
                        }else{
                            //Signing-Up Failed
                            Toast.makeText(this@MainActivity2, "Sign-Up failed. Please try again", Toast.LENGTH_SHORT).show()
                        }
                    }
                    override fun onFailure(call: Call<SignUpResponse>, t: Throwable ){
                        Toast.makeText(this@MainActivity2, "Network error. Please Try Again", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        })

    }
}
