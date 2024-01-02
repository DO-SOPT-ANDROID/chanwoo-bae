package org.sopt.dosopttemplate.data.repositoryimpl

import org.sopt.dosopttemplate.data.dto.remote.request.RequestLoginDto
import org.sopt.dosopttemplate.data.dto.remote.request.RequestSignUpDto
import org.sopt.dosopttemplate.data.service.AuthService

class AuthRepository(private val authService: AuthService) {
    suspend fun postLogin(user: RequestLoginDto) =
        kotlin.runCatching {
            authService.postLogin(user)
        }

    suspend fun postSignUp(signUpInfo: RequestSignUpDto) =
        kotlin.runCatching {
            authService.postSignUp(signUpInfo)
        }
}
