package org.sopt.dosopttemplate.data.datasourceimpl.remote

import org.sopt.dosopttemplate.data.datasource.remote.ReqresDataSource
import org.sopt.dosopttemplate.data.dto.remote.respose.ResponseReqresDto
import org.sopt.dosopttemplate.data.service.ReqresService

class ReqresDataSourceImpl(
    private val reqresService: ReqresService,
) : ReqresDataSource {
    override suspend fun getReqresList(page: Int): ResponseReqresDto {
        return reqresService.getUserList(page)
    }
}
