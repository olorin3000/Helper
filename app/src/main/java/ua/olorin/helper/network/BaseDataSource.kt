package ua.olorin.helper.network

import android.util.Log
import retrofit2.Response
import ua.olorin.helper.data.Result

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Result.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Result<T> {
        Log.e("DataSource", "Message: $message")
        return Result.error(message)
    }
}