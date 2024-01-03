package org.sopt.dosopttemplate.data.datasourceimpl.remote

import org.sopt.dosopttemplate.data.datasource.remote.AuthDataSource
import org.sopt.dosopttemplate.data.dto.remote.request.RequestLoginDto
import org.sopt.dosopttemplate.data.dto.remote.request.RequestSignUpDto
import org.sopt.dosopttemplate.data.dto.remote.respose.ResponseLoginDto
import org.sopt.dosopttemplate.data.service.AuthService

class AuthDataSourceImpl(private val authService: AuthService) : AuthDataSource {
    override suspend fun login(request: RequestLoginDto): ResponseLoginDto =
        authService.postLogin(request)

    override suspend fun signUp(request: RequestSignUpDto): Unit =
        authService.postSignUp(request)
}
