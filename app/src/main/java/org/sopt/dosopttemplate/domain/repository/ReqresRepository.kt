package org.sopt.dosopttemplate.domain.repository

import org.sopt.dosopttemplate.domain.entity.ReqresEntity

interface ReqresRepository {
    suspend fun getReqresList(page: Int): Result<List<ReqresEntity>>
}
