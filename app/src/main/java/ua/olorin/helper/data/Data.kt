package ua.olorin.helper.data

data class Data (
    val avatar: String,
    val name: String,
    val uid: String,
    val ratingSpeed: Float,
    val numberOfOrders: Int,
    val numberOfOrdersComplete: Int,
    val ratingPunctuality: Float,
    val workSchedule: WorkSchedule,
    val services: List<Service>
)