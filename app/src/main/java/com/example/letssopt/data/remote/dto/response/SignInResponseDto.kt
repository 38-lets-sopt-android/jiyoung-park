package com.example.letssopt.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInResponseDto (
    @SerialName("success")
    val success: Boolean,
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String,
    @SerialName("code")
    val code: String,
    @SerialName("data")
    val data: Int,
    @SerialName("meta")
    val meta: UserData? = null,
)

@Serializable
data class UserData(
    @SerialName("userId")
    val userId: Int,
)