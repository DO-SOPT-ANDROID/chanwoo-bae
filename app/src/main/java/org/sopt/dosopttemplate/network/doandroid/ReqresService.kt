package org.sopt.dosopttemplate.network.doandroid

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ReqresService {
    @GET("api/users")
    fun getUserList(
        @Query("page") page: Int,
    ): Call<ResponseReqresDto>
}
