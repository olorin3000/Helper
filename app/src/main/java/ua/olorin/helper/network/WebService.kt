package ua.olorin.helper.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import ua.olorin.helper.data.Result

interface WebService {

    @GET("olorin3000/Helper/{path}")
    suspend fun getServerData(@Path("path") path: String) : Response<ResponseWrapper>
}