package org.sopt.dosopttemplate.data.service

import org.sopt.dosopttemplate.data.dto.remote.request.RequestLoginDto
import org.sopt.dosopttemplate.data.dto.remote.request.RequestSignUpDto
import org.sopt.dosopttemplate.data.dto.remote.respose.ResponseLoginDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/v1/members/sign-in")
    suspend fun postLogin(
        @Body request: RequestLoginDto,
    ): ResponseLoginDto

    @POST("api/v1/members")
    suspend fun postSignUp(
        @Body request: RequestSignUpDto,
    ): Unit
}
