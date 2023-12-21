fun main() {

    /***
     * part1:
     * camel poker: sort the hands by their value
     */
    fun part1(input: List<String>): Int {
        val completelySortedList = mutableListOf<Int>()
        var sum = 0

        val pokerHandComparator = object : Comparator<String> {
            override fun compare(hand1: String, hand2: String): Int {
                // custom logic for comparing symbols
                val order = mapOf(
                    'A' to 14,
                    'K' to 13,
                    'Q' to 12,
                    'J' to 11,
                    'T' to 10,
                    '9' to 9,
                    '8' to 8,
                    '7' to 7,
                    '6' to 6,
                    '5' to 5,
                    '4' to 4,
                    '3' to 3,
                    '2' to 2
                )

                for (i in 0..hand1.length) {
                    val symbol = order.getOrDefault(hand2[i], -1) - order.getOrDefault(hand1[i], -1)
                    if (symbol != 0) {
                        return symbol
                    }
                }
                return 0
            }
        }

        // sort all poker hands into their respective category
        val fives = mutableListOf<String>()
        val fours = mutableListOf<String>()
        val fullhouses = mutableListOf<String>()
        val threes = mutableListOf<String>()
        val twoPairs = mutableListOf<String>()
        val onePairs = mutableListOf<String>()
        val highCards = mutableListOf<String>()
        input.forEach { s: String ->
            val hand = s.split(' ').first()
            if (isFive(hand)) {
                fives.add(s)
            } else if (isFour(hand)) {
                fours.add(s)
            } else if (isFullHouse(hand)) {
                fullhouses.add(s)
            } else if (isThree(hand)) {
                threes.add(s)
            } else if (isTwoPair(hand)) {
                twoPairs.add(s)
            } else if (isOnePair(hand)) {
                onePairs.add(s)
            } else {
                highCards.add(s)
            }
        }

        // sort the poker hands within their respective categories
        val highCardsSorted = highCards.sortedWith(pokerHandComparator).reversed()
        val onePairsSorted = onePairs.sortedWith(pokerHandComparator).reversed()
        val twoPairsSorted = twoPairs.sortedWith(pokerHandComparator).reversed()
        val threesSorted = threes.sortedWith(pokerHandComparator).reversed()
        val fullhousesSorted = fullhouses.sortedWith(pokerHandComparator).reversed()
        val foursSorted = fours.sortedWith(pokerHandComparator).reversed()
        val fivesSorted = fives.sortedWith(pokerHandComparator).reversed()

        // save the bid amounts of all poker hands sorted within one list
        highCardsSorted.forEach { s: String ->
            completelySortedList.add(s.split(' ').last().toInt())
        }
        onePairsSorted.forEach { s: String ->
            completelySortedList.add(s.split(' ').last().toInt())
        }
        twoPairsSorted.forEach { s: String ->
            completelySortedList.add(s.split(' ').last().toInt())
        }
        threesSorted.forEach { s: String ->
            completelySortedList.add(s.split(' ').last().toInt())
        }
        fullhousesSorted.forEach { s: String ->
            completelySortedList.add(s.split(' ').last().toInt())
        }
        foursSorted.forEach { s: String ->
            completelySortedList.add(s.split(' ').last().toInt())
        }
        fivesSorted.forEach { s: String ->
            completelySortedList.add(s.split(' ').last().toInt())
        }

        completelySortedList.forEachIndexed { index, number ->
            sum += (index + 1) * number
        }

        return sum
    }

    /***
     * part2:
     * camel poker but with jokers now
     */
    fun part2(input: List<String>): Int {

        val completelySortedList = mutableListOf<Int>()
        var sum = 0

        val pokerHandComparatorWithJokers = object : Comparator<String> {
            override fun compare(hand1: String, hand2: String): Int {
                // custom logic for comparing symbols
                val order = mapOf(
                    'A' to 13,
                    'K' to 12,
                    'Q' to 11,
                    'T' to 10,
                    '9' to 9,
                    '8' to 8,
                    '7' to 7,
                    '6' to 6,
                    '5' to 5,
                    '4' to 4,
                    '3' to 3,
                    '2' to 2,
                    'J' to 1
                )

                for (i in 0..hand1.length) {
                    val symbol = order.getOrDefault(hand2[i], -1) - order.getOrDefault(hand1[i], -1)
                    if (symbol != 0) {
                        return symbol
                    }
                }
                return 0
            }
        }

        // sort all poker hands into their respective category
        val fives = mutableListOf<String>()
        val fours = mutableListOf<String>()
        val fullhouses = mutableListOf<String>()
        val threes = mutableListOf<String>()
        val twoPairs = mutableListOf<String>()
        val onePairs = mutableListOf<String>()
        val highCards = mutableListOf<String>()
        input.forEach { s: String ->
            // if there is no joker, it is the same logic as before
            val hand = s.split(' ').first()
            if (!s.contains('J')) {
                if (isFive(hand)) {
                    fives.add(s)
                } else if (isFour(hand)) {
                    fours.add(s)
                } else if (isFullHouse(hand)) {
                    fullhouses.add(s)
                } else if (isThree(hand)) {
                    threes.add(s)
                } else if (isTwoPair(hand)) {
                    twoPairs.add(s)
                } else if (isOnePair(hand)) {
                    onePairs.add(s)
                } else {
                    highCards.add(s)
                }
            } else {
                val categoryNumber = categorizeJokers(hand)
                when (categoryNumber) {
                    0 -> highCards.add(s)
                    1 -> onePairs.add(s)
                    2 -> twoPairs.add(s)
                    3 -> threes.add(s)
                    4 -> fullhouses.add(s)
                    5 -> fours.add(s)
                    6 -> fives.add(s)
                }
            }
        }

        // sort the poker hands within their respective categories
        val highCardsSorted = highCards.sortedWith(pokerHandComparatorWithJokers).reversed()
        val onePairsSorted = onePairs.sortedWith(pokerHandComparatorWithJokers).reversed()
        val twoPairsSorted = twoPairs.sortedWith(pokerHandComparatorWithJokers).reversed()
        val threesSorted = threes.sortedWith(pokerHandComparatorWithJokers).reversed()
        val fullhousesSorted = fullhouses.sortedWith(pokerHandComparatorWithJokers).reversed()
        val foursSorted = fours.sortedWith(pokerHandComparatorWithJokers).reversed()
        val fivesSorted = fives.sortedWith(pokerHandComparatorWithJokers).reversed()

        // save the bid amounts of all poker hands sorted within one list
        highCardsSorted.forEach { s: String ->
            completelySortedList.add(s.split(' ').last().toInt())
        }
        onePairsSorted.forEach { s: String ->
            completelySortedList.add(s.split(' ').last().toInt())
        }
        twoPairsSorted.forEach { s: String ->
            completelySortedList.add(s.split(' ').last().toInt())
        }
        threesSorted.forEach { s: String ->
            completelySortedList.add(s.split(' ').last().toInt())
        }
        fullhousesSorted.forEach { s: String ->
            completelySortedList.add(s.split(' ').last().toInt())
        }
        foursSorted.forEach { s: String ->
            completelySortedList.add(s.split(' ').last().toInt())
        }
        fivesSorted.forEach { s: String ->
            completelySortedList.add(s.split(' ').last().toInt())
        }

        completelySortedList.forEachIndexed { index, number ->
            sum += (index + 1) * number
        }

        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_1_test")
    check(part1(testInput) == 6440)
    check(part2(testInput) == 5905)

    val input = readInput("Day07")
    part1(input).println()
    part2(input).println()

    // check after submitting the solution
    check(part1(input) == 250898830)
    check(part2(input) == 252127335)
}

val validSymbols = listOf('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2')

fun isFive(input: String): Boolean {
    return input[0] == input[1] && input[1] == input[2] && input[2] == input[3] && input[3] == input[4]
}

fun isFour(input: String): Boolean {
    val numbers = mutableListOf<Int>()
    // count the appearance of each symbol within a poker hand
    validSymbols.forEach { symbol: Char ->
        numbers.add(input.count { it == symbol })
    }
    return numbers.contains(4)
}

fun isFullHouse(input: String): Boolean {
    val numbers = mutableListOf<Int>()
    validSymbols.forEach { symbol: Char ->
        numbers.add(input.count { it == symbol })
    }
    return numbers.contains(3) && numbers.contains(2)
}

fun isThree(input: String): Boolean {
    val numbers = mutableListOf<Int>()
    validSymbols.forEach { symbol: Char ->
        numbers.add(input.count { it == symbol })
    }
    return numbers.contains(3)
}

fun isTwoPair(input: String): Boolean {
    val numbers = mutableListOf<Int>()
    validSymbols.forEach { symbol: Char ->
        numbers.add(input.count { it == symbol })
    }
    return numbers.filter { it == 2 }.size == 2
}

fun isOnePair(input: String): Boolean {
    val numbers = mutableListOf<Int>()
    validSymbols.forEach { symbol: Char ->
        numbers.add(input.count { it == symbol })
    }
    return numbers.filter { it == 2 }.size == 1
}

fun categorizeJokers(hand: String): Int {
    // find the symbol with the highest occurrence within the hand and replace all jokers with this symbol
    val symbolCountMap = mutableMapOf<Char, Int>()
    symbolCountMap += mapOf('A' to hand.count { it == 'A' })
    symbolCountMap += mapOf('K' to hand.count { it == 'K' })
    symbolCountMap += mapOf('Q' to hand.count { it == 'Q' })
    symbolCountMap += mapOf('T' to hand.count { it == 'T' })
    symbolCountMap += mapOf('9' to hand.count { it == '9' })
    symbolCountMap += mapOf('8' to hand.count { it == '8' })
    symbolCountMap += mapOf('7' to hand.count { it == '7' })
    symbolCountMap += mapOf('6' to hand.count { it == '6' })
    symbolCountMap += mapOf('5' to hand.count { it == '5' })
    symbolCountMap += mapOf('4' to hand.count { it == '4' })
    symbolCountMap += mapOf('3' to hand.count { it == '3' })
    symbolCountMap += mapOf('2' to hand.count { it == '2' })
    val maxChar = symbolCountMap.maxBy { it.value }
    val replacedHand = hand.replace('J', maxChar.key)
    // categorize the hand with the replaced jokers
    return when {
        isFive(replacedHand) -> 6
        isFour(replacedHand) -> 5
        isFullHouse(replacedHand) -> 4
        isThree(replacedHand) -> 3
        isTwoPair(replacedHand) -> 2
        isOnePair(replacedHand) -> 1
        else -> 0
    }
}