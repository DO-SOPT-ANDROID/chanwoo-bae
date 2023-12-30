package org.sopt.dosopttemplate.data.datasource.remote

import org.sopt.dosopttemplate.data.dto.remote.respose.ResponseReqresDto

interface ReqresDataSource {
    suspend fun getReqresList(page: Int): ResponseReqresDto
}
