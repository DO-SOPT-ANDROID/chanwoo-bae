package org.sopt.dosopttemplate.data.datasourceimpl.remote

import org.sopt.dosopttemplate.data.datasource.remote.ReqresDataSource
import org.sopt.dosopttemplate.data.dto.remote.respose.ResponseReqresDto
import org.sopt.dosopttemplate.data.service.ReqresService

// 실제 데이터 소스를 구현하는 클래스, 네트워크 호출을 통해 데이터를 가져오는 역할
class ReqresDataSourceImpl(
    private val reqresService: ReqresService,
) : ReqresDataSource {
    override suspend fun getReqresList(page: Int): ResponseReqresDto {
        return reqresService.getUserList(page)
    }
}
