package solutions.CHK

object CheckoutSolution {
    fun checkout(skus: String): Int {
        val items : Map<String, Int> = mapOf("A" to 50, "B" to 30, "C" to 20, "D" to 15)
        var totalValue = 0

        // deals - hardcoded version
        var Acount = 0
        var Bcount = 0

        for (item in skus) {
            val itemString = item.toString()

            if (items.contains(itemString)) {

                //basecase
                totalValue += items[itemString]!!

                if (itemString == "A") {
                    Acount++

                    if (Acount >= 3) {
                        Acount = 0
                        // 3 * 50 - 130 = 20
                        totalValue -= 20
                    }
                }

                if (itemString == "B") {
                    Bcount++

                    if (Bcount >= 2) {
                        Bcount = 0
                        // 2 * 30 - 45 = 15
                        totalValue -= 15
                    }
                }

            } else {
                return -1
            }
        }

        return totalValue
    }
}
