package ua.olorin.helper.network

import ua.olorin.helper.data.Data

data class ResponseWrapper (val success: Boolean, val errors: String?, val data: List<Data>)