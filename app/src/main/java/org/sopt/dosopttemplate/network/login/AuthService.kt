package org.sopt.dosopttemplate.network.login

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/v1/members/sign-in")
    suspend fun postLogin(
        @Body request: RequestLoginDto,
    ): Response<ResponseLoginDto>

    @POST("api/v1/members")
    suspend fun postSignUp(
        @Body request: RequestSignUpDto,
    ): Response<Unit>
}
