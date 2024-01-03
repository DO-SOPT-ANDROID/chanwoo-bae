package org.sopt.dosopttemplate.data.service

import org.sopt.dosopttemplate.data.dto.remote.respose.ResponseReqresDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ReqresService {
    @GET("api/users")
    suspend fun getUserList(
        @Query("page") page: Int,
    ): ResponseReqresDto
}
