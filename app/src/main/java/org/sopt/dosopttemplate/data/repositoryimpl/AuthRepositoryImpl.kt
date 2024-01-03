package org.sopt.dosopttemplate.data.repositoryimpl

import org.sopt.dosopttemplate.data.datasource.remote.AuthDataSource
import org.sopt.dosopttemplate.data.dto.remote.request.RequestLoginDto
import org.sopt.dosopttemplate.data.dto.remote.request.RequestSignUpDto
import org.sopt.dosopttemplate.domain.entity.UserEntity
import org.sopt.dosopttemplate.domain.entity.UserRequestEntity
import org.sopt.dosopttemplate.domain.repository.AuthDomainRepository

class AuthRepositoryImpl(private val authDataSource: AuthDataSource) : AuthDomainRepository {
    override suspend fun login(userRequestEntity: UserRequestEntity): Result<UserEntity> =
        kotlin.runCatching {
            authDataSource.login(
                RequestLoginDto(
                    userRequestEntity.userName,
                    userRequestEntity.passWord,
                ),
            ).toUserEntity()
        }

    override suspend fun signUp(userEntity: UserEntity): Result<Unit> =
        kotlin.runCatching {
            authDataSource.signUp(
                RequestSignUpDto(
                    userEntity.id,
                    userEntity.pwd,
                    userEntity.nickName,
                ),
            )
        }
}
