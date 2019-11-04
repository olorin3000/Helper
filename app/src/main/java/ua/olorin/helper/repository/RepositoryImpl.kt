package ua.olorin.helper.repository

import ua.olorin.helper.data.Result
import ua.olorin.helper.network.ResponseWrapper
import ua.olorin.helper.network.WebService
import ua.olorin.helper.network.BaseDataSource

class RepositoryImpl(private val webService: WebService) : Repository, BaseDataSource(){
    override suspend fun getServerData(path: String): Result<ResponseWrapper> {
        return getResult { webService.getServerData(path) }
    }

}