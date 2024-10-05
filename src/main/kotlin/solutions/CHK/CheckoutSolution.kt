package solutions.CHK

object CheckoutSolution {
    fun checkout(skus: String): Int {
        // check for illegal values
        for (item in skus) {
            if (!skus.contains(item.toString())) return -1
        }

        val itemMap : MutableMap<String, Int> = mutableMapOf()
        var totalValue = 0

        skus.forEach { item ->
            itemMap[item.toString()] = itemMap.getOrDefault(item.toString(), 0) + 1
        }

        val Ecount = itemMap.getOrDefault("E", 1)

        return totalValue
    }
}
