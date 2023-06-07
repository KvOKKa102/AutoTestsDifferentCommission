import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Test

class CalculateCommissionKtTest {

    @Test
    fun calculateCommission_MastercardTotalsumAboveLimit() {
        val cardType = "Mastercard"
        val previousTransfersAmount = 50_000
        val transferAmount = 30_000
        val transferType = "card"

        val commission = calculateCommission(cardType, previousTransfersAmount, transferAmount, transferType)

        assertEquals(218, commission)
    }

    @Test
    fun calculateCommission_MastercardWithTotalsumBelowLimit() {
        val cardType = "Mastercard"
        val previousTransfersAmount = 50_000
        val transferAmount = 10_000
        val transferType = "card"

        val commission = calculateCommission(cardType, previousTransfersAmount, transferAmount, transferType)

        assertEquals(0, commission)
    }

    @Test
    fun calculateCommission_MirWithCommissionBelowMinimum() {
        val cardType = "Mir"
        val previousTransfersAmount = 10_000
        val transferAmount = 5_000
        val transferType = "card"

        val commission = calculateCommission(cardType, previousTransfersAmount, transferAmount, transferType)

        assertEquals(35, commission)
    }

    @Test
    fun calculateCommission_MirWithCommissionBelowMinimumCorrect() {
        val cardType = "Mir"
        val previousTransfersAmount = 10_000
        val transferAmount = 4_000
        val transferType = "card"

        val commission = calculateCommission(cardType, previousTransfersAmount, transferAmount, transferType)

        assertEquals(35, commission)
    }

    @Test
    fun calculateCommission_MirWithCommissionAboveMinimum() {
        val cardType = "Mir"
        val previousTransfersAmount = 10_000
        val transferAmount = 10_000
        val transferType = "card"

        val commission = calculateCommission(cardType, previousTransfersAmount, transferAmount, transferType)

        assertEquals(75, commission)
    }

    @Test
    fun calculateCommission_VkPayWithTotalsumBelowLimit() {
        val cardType = "VK Pay"
        val previousTransfersAmount = 20_000
        val transferAmount = 10_000
        val transferType = "VK Pay"

        val commission = calculateCommission(cardType, previousTransfersAmount, transferAmount, transferType)

        assertEquals(0, commission)
    }

    @Test
    fun calculateCommission_VkPayWithTotalsumAboveLimit () {
        val cardType = "VK Pay"
        val previousTransfersAmount = 30_000
        val transferAmount = 20_000
        val transferType = "VK Pay"

        try {
            calculateCommission(cardType, previousTransfersAmount, transferAmount, transferType)
            fail("Expected an Exception to be thrown")
        } catch (e: Exception) {
            assertEquals("Сумма переводов на VK Pay в текущем месяце превышает месячный лимит", e.message)
        }
    }

}