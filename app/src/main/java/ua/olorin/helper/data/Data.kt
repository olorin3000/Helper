package ua.olorin.helper.data

data class Data (
    val avatar: String,
    val name: String,
    val uid: String,
    val ratingSpeed: Int,
    val numberOfOrders: Int,
    val numberOfOrdersComplete: Int,
    val ratingPunctuality: Int,
    val workSchedule: WorkSchedule,
    val services: List<Service>
)