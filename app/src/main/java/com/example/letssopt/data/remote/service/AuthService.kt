package com.example.letssopt.data.remote.service

import com.example.letssopt.data.remote.dto.request.SignInRequestDto
import com.example.letssopt.data.remote.dto.request.SignUpRequestDto
import com.example.letssopt.data.remote.dto.response.SignInResponseDto
import com.example.letssopt.data.remote.dto.response.SignUpResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/api/v1/auth/signup")
    suspend fun signUp(@Body request: SignUpRequestDto): Response<SignUpResponseDto>

    @POST("/api/v1/auth/signin")
    suspend fun signIn(@Body request: SignInRequestDto): Response<SignInResponseDto>
}