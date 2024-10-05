package solutions.CHK

object CheckoutSolution {
    fun checkout(skus: String): Int {
        val prices = mapOf("A" to 50, "B" to 30, "C" to 20, "D" to 15 , "E" to 40)
        // check for illegal values
        for (item in skus) {
            if (!skus.contains(item.toString())) return -1
        }

        val itemMap : MutableMap<String, Int> = mutableMapOf()
        var totalValue = 0

        skus.forEach { item ->
            itemMap[item.toString()] = itemMap.getOrDefault(item.toString(), 0) + 1
        }

        // apply E deal
        val Ediscount = itemMap.getOrDefault("E", 0).div(2)
        itemMap["B"] = if (itemMap.getOrDefault("B", 0) - Ediscount < 0) {
            0
        } else {
            itemMap.getOrDefault("B", 0) - Ediscount
        }

        itemMap.forEach{ item ->
            val pricePerItem = prices[item.key]

            if (item.key == "A") {
                
            }

        }

        return totalValue
    }
}


