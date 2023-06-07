fun main() {
    try {
        val commission = calculateCommission(
            "Visa", previousTransfersAmount = 25000,
            transferAmount = 1000, transferType = "VK Pay"
        )
        println("Комиссия за перевод: $commission рублей")
    } catch (e: Exception) {
        println("Ошибка: ${e.message}")
    }

    try {
        val commission = calculateCommission(
            "Mastercard", previousTransfersAmount = 75000,
            transferAmount = 1000, transferType = "card"
        )
        println("Комиссия за перевод: $commission рублей")
    } catch (e: Exception) {
        println("Ошибка: ${e.message}")
    }
}
