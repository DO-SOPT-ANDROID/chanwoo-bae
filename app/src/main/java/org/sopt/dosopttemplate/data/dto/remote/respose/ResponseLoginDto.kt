package org.sopt.dosopttemplate.data.dto.remote.respose

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.dosopttemplate.domain.entity.UserEntity

@Serializable
data class ResponseLoginDto(
    @SerialName("id")
    val id: Int,
    @SerialName("username")
    val username: String,
    @SerialName("nickname")
    val nickname: String,
) {
    fun toUserEntity() = UserEntity(
        id = username,
        pwd = "",
        nickName = nickname,
        mbti = "",
    )
}
