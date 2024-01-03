package org.sopt.dosopttemplate.data.datasource.remote

import org.sopt.dosopttemplate.data.dto.remote.request.RequestLoginDto
import org.sopt.dosopttemplate.data.dto.remote.request.RequestSignUpDto
import org.sopt.dosopttemplate.data.dto.remote.respose.ResponseLoginDto

interface AuthDataSource {
    suspend fun login(request: RequestLoginDto): ResponseLoginDto
    suspend fun signUp(request: RequestSignUpDto): Unit
}
