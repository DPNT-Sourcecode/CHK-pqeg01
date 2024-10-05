package solutions.CHK

object CheckoutSolution {
    fun checkout(skus: String): Int {

//        +------+-------+------------------------+
//        | Item | Price | Special offers         |
//        +------+-------+------------------------+
//        | A    | 50    | 3A for 130, 5A for 200 |
//        | B    | 30    | 2B for 45              |
//        | C    | 20    |                        |
//        | D    | 15    |                        |
//        | E    | 40    | 2E get one B free      |
//        | F    | 10    | 2F get one F free      |
//        | G    | 20    |                        |
//        | H    | 10    | 5H for 45, 10H for 80  | double
//        | I    | 35    |                        |
//        | J    | 60    |                        |
//        | K    | 80    | 2K for 150             | single
//        | L    | 90    |                        |
//        | M    | 15    |                        |
//        | N    | 40    | 3N get one M free      | get diff free
//        | O    | 10    |                        |
//        | P    | 50    | 5P for 200             | single
//        | Q    | 30    | 3Q for 80              | single
//        | R    | 50    | 3R get one Q free      | get diff free
//        | S    | 30    |                        |
//        | T    | 20    |                        |
//        | U    | 40    | 3U get one U free      | get same free
//        | V    | 50    | 2V for 90, 3V for 130  | double
//        | W    | 20    |                        |
//        | X    | 90    |                        |
//        | Y    | 10    |                        |
//        | Z    | 50    |                        |
//        +------+-------+------------------------+

        val prices = mapOf(
            "A" to 50,
            "B" to 30,
            "C" to 20,
            "D" to 15,
            "E" to 40,
            "F" to 10,
            "G" to 20,
            "H" to 10,
            "I" to 35,
            "J" to 60,
            "K" to 80,
            "L" to 90,
            "M" to 15,
            "N" to 40,
            "O" to 10,
            "P" to 50,
            "Q" to 30,
            "R" to 50,
            "S" to 30,
            "T" to 20,
            "U" to 40,
            "V" to 50,
            "W" to 20,
            "X" to 90,
            "Y" to 10,
            "Z" to 50,
            )

        // check for illegal values
        for (item in skus) {
            if (!prices.contains(item.toString())) return -1
        }

        val itemMap : MutableMap<String, Int> = mutableMapOf()
        var totalValue = 0

        //helpers
        fun buyXGetOneFree(item1: String, item2: String, value: Int) {
            val itemDiscount = itemMap.getOrDefault(item1, 0).div(value)
            itemMap[item2] = if (itemMap.getOrDefault(item2, 0) - itemDiscount < 0) {
                0
            } else {
                itemMap.getOrDefault(item2, 0) - itemDiscount
            }
        }

        fun applyDoubleDeal(firstMultiplier: Int, firstDeal: Int, secondMultiplier: Int, secondDeal: Int, itemPrice: Int, itemCount: Int): Int {
            val fCount = itemCount / firstMultiplier
            val after = itemCount - fCount * firstMultiplier
            val sCount = after / secondMultiplier
            val remainder = after % secondMultiplier

            return fCount * firstDeal + sCount * secondDeal + remainder * itemPrice
        }

        fun applySingleDeal(multiplier: Int, deal: Int, itemPrice: Int, itemCount: Int): Int {
            val count = itemCount / multiplier
            val remainder = itemCount - count * multiplier

            return count * deal + remainder * itemPrice
        }

        // map occurrence of each items
        skus.forEach { item ->
            itemMap[item.toString()] = itemMap.getOrDefault(item.toString(), 0) + 1
        }

        // do this first since this can affect other deals
        // apply E to B deal (2) - TODO: maybe move this to when block somehow
        buyXGetOneFree("E", "B", 2)

        // N to M deal (3)
        buyXGetOneFree("N", "M", 3)

        // R to Q deal (3)
        buyXGetOneFree("R", "Q", 3)

        itemMap.forEach{ item ->
            val pricePerItem = prices[item.key]!!

            when (item.key) {
                "A" -> {
                    totalValue += applyDoubleDeal(5, 200, 3, 130, pricePerItem, item.value)
                }
                "B" -> {
                    totalValue += applySingleDeal(2, 45, pricePerItem, item.value)
                }
                "F" -> {
                    totalValue += if (item.value >= 3) {
                        (item.value - (item.value / 3)) * pricePerItem
                    } else {
                        item.value * pricePerItem
                    }
                }
                else -> {
                    totalValue += item.value * pricePerItem
                }
            }

        }

        return totalValue
    }
}
