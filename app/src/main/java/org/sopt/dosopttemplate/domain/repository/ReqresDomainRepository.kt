package org.sopt.dosopttemplate.domain.repository

import org.sopt.dosopttemplate.domain.entity.ReqresEntity

/*ReqresRepository 인터페이스는 도메인(Domain) 레이어에서 사용되며,
원격 데이터 소스(ReqresDataSource)로부터 Reqres 사용자 리스트를 가져오는 기능을 정의*/
interface ReqresDomainRepository {
    suspend fun getReqresList(page: Int): Result<List<ReqresEntity>>
}
