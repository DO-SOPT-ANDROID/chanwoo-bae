package org.sopt.dosopttemplate.data.dto.remote.respose

import kotlinx.serialization.Serializable
import org.sopt.dosopttemplate.domain.entity.ReqresEntity

@Serializable
data class ResponseReqresDto(
    val data: List<Data>,
    val page: Int,
    val per_page: Int,
    val support: Support,
    val total: Int,
    val total_pages: Int,
) {
    @Serializable
    data class Data(
        val avatar: String,
        val email: String,
        val first_name: String,
        val id: Int,
        val last_name: String,
    )

    @Serializable
    data class Support(
        val text: String,
        val url: String,
    )

    fun toReqresList(): List<ReqresEntity> = data.map {
        ReqresEntity(
            id = it.id,
            email = it.email,
            first_name = it.first_name,
            last_name = it.last_name,
            avatar = it.avatar,
        )
    }
}
