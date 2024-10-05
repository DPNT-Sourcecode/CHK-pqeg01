package solutions.CHK

object CheckoutSolution {
    fun checkout(skus: String): Int {
        val items : Map<String, Int> = mapOf("A" to 50, "B" to 30, "C" to 20, "D" to 15)
        var totalValue = 0

        // deals - hardcoded version
        var Acount = 0
        var Bcount = 0

        for (item in skus) {
            if (items.containsKey(item)) {

            }
        }

        return -1
    }
}
