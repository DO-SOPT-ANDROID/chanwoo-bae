package org.sopt.dosopttemplate.data.repositoryimpl

import org.sopt.dosopttemplate.data.datasource.remote.ReqresDataSource
import org.sopt.dosopttemplate.domain.entity.ReqresEntity
import org.sopt.dosopttemplate.domain.repository.ReqresRepository

// 외부 데이터 소스(ReqresDataSource)로부터 데이터를 가져오고, 필요한 형태로 변환하여 비즈니스 로직 계층에게 제공
class ReqresRepositoryImpl(private val reqresDataSource: ReqresDataSource) :
    ReqresRepository {
    override suspend fun getReqresList(page: Int): Result<List<ReqresEntity>> {
        return runCatching {
            reqresDataSource.getReqresList(page).toReqresList()
        }
    }
}
