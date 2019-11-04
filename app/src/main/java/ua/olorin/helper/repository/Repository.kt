package ua.olorin.helper.repository

import ua.olorin.helper.data.Result
import ua.olorin.helper.network.ResponseWrapper

interface Repository {
    suspend fun getServerData(path: String) : Result<ResponseWrapper>
}