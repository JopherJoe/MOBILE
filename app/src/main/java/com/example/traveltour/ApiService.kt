package com.example.traveltour

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

data class SignUp(
    val firstname: String,
    val lastname: String,
    val email: String,
    val password: String,
    val contact_no: String
)
data class SignUpResponse(
    val message: String,
    val id: Int,
)

interface UserSignUp {
    @POST("user/sign-up")
    fun signUp(@Body signUp: SignUp): Call<SignUpResponse>

    // Add more API endpoints as needed
}