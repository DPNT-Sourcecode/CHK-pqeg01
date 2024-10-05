package solutions.CHK

object CheckoutSolution {
    fun checkout(skus: String): Int {
        val items : Map<String, Int> = mapOf("A" to 50, "B" to 30, "C" to 20, "D" to 15, "E" to 40)
        var totalValue = 0

        // check for illegal values
        for (item in items) {
            if (!items.contains(item.toString())) return -1
        }

        val itemMap : MutableMap<String, Int> = mutableMapOf()

        skus.forEach { item ->
            itemMap[item.toString()] = itemMap.getOrDefault(item.toString(), 0) + 1
        }



        return totalValue
    }
}