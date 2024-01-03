package org.sopt.dosopttemplate.domain.repository

import org.sopt.dosopttemplate.domain.entity.UserEntity
import org.sopt.dosopttemplate.domain.entity.UserRequestEntity

interface AuthDomainRepository {
    suspend fun login(userRequestEntity: UserRequestEntity): Result<UserEntity>
    suspend fun signUp(userEntity: UserEntity): Result<Unit>
}
