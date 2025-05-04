package com.example.safeflow

data class SignupState(
    val isLoading: Boolean = false,
    val phoneNumber: String = "",
    val otpSent: Boolean = false,
    val otpResent: Boolean = false,
    val otpVerified: Boolean = false,
    val signupComplete: Boolean = false,
    val errorMessage: String? = null,
    val userData: UserData? = null
)

 data class UserData(
    val fullName: String,
    val email: String,
 )