fun main() {

    fun part1(input: List<String>): Int {

        var completelySortedList = mutableListOf<Int>()
        var sum = 0

        val customComparator = object : Comparator<List<Char>> {
            override fun compare(o1: List<Char>, o2: List<Char>): Int {
                // Custom logic for comparing letters
                val order = mapOf(
                    'A' to 14,
                    'Q' to 13,
                    'K' to 12,
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

                var firstletter = order.getOrDefault(o2.first(), -1) - order.getOrDefault(o1.first(), -1)
                if (firstletter != 0) {
                    return firstletter
                }
                var secondletter = order.getOrDefault(o2[1], -1) - order.getOrDefault(o1[1], -1)
                if (secondletter != 0) {
                    return secondletter
                }
                var thirdletter = order.getOrDefault(o2[2], -1) - order.getOrDefault(o1[2], -1)
                if (thirdletter != 0) {
                    return thirdletter
                }
                var fourthletter = order.getOrDefault(o2[3], -1) - order.getOrDefault(o1[3], -1)
                if (fourthletter != 0) {
                    return fourthletter
                }
                var fifthletter = order.getOrDefault(o2[4], -1) - order.getOrDefault(o1[4], -1)
                if (fifthletter != 0) {
                    return fifthletter
                }
                return 0
            }
        }

        val fives = mutableListOf<String>()
        var fours = mutableListOf<String>()
        val fullhouses = mutableListOf<String>()
        var threes = mutableListOf<String>()
        var twoPairs = mutableListOf<String>()
        val onePairs = mutableListOf<String>()
        val highCards = mutableListOf<String>()
        input.forEach { s: String ->
            val chunks = s.split(' ')
            if (isFive(chunks.first())) {
                fives.add(s)
            } else if (isFour(chunks.first())) {
                fours.add(s)
            } else if (isFullHouse(chunks.first())) {
                fullhouses.add(s)
            } else if (isThree(chunks.first())) {
                threes.add(s)
            } else if (isTwoPair(chunks.first())) {
                twoPairs.add(s)
            } else if (isOnePair(chunks.first())) {
                onePairs.add(s)
            } else {
                highCards.add(s)
            }
        }

        println(highCards)
        println(onePairs)
        println(twoPairs)
        println(threes)
        println(fullhouses)
        println(fours)
        println(fives)

        var fivesCharArray = mutableListOf<MutableList<Char>>()
        fives.forEach { fivesCharArray.add(it.toCharArray().toMutableList()) }
        var fivesSorted = fivesCharArray.sortedWith(customComparator).reversed()
        var foursCharArray = mutableListOf<MutableList<Char>>()
        fours.forEach { foursCharArray.add(it.toCharArray().toMutableList()) }
        var foursSorted = foursCharArray.sortedWith(customComparator).reversed()
        var fullHousesCharArray = mutableListOf<MutableList<Char>>()
        fullhouses.forEach { fullHousesCharArray.add(it.toCharArray().toMutableList()) }
        var fullHousesSorted = fullHousesCharArray.sortedWith(customComparator).reversed()
        var threesCharArray = mutableListOf<MutableList<Char>>()
        threes.forEach { threesCharArray.add(it.toCharArray().toMutableList()) }
        var threesSorted = threesCharArray.sortedWith(customComparator).reversed()
        var twoPairsCharArray = mutableListOf<MutableList<Char>>()
        twoPairs.forEach { twoPairsCharArray.add(it.toCharArray().toMutableList()) }
        var twoPairsSorted = twoPairsCharArray.sortedWith(customComparator).reversed()
        var onePairsCharArray = mutableListOf<MutableList<Char>>()
        onePairs.forEach { onePairsCharArray.add(it.toCharArray().toMutableList()) }
        var onePairsSorted = onePairsCharArray.sortedWith(customComparator).reversed()
        var highCardsCharArray = mutableListOf<MutableList<Char>>()
        highCards.forEach { highCardsCharArray.add(it.toCharArray().toMutableList()) }
        var highCardsSorted = highCardsCharArray.sortedWith(customComparator).reversed()

        highCardsSorted.forEach { char: MutableList<Char> ->
            val sublist = char.subList(6, char.size)
            var tollerString = ""
            sublist.forEach { tollerString += it }
            completelySortedList.add(tollerString.toInt())
        }
        onePairsSorted.forEach { char: MutableList<Char> ->
            val sublist = char.subList(6, char.size)
            var tollerString = ""
            sublist.forEach { tollerString += it }
            completelySortedList.add(tollerString.toInt())
        }
        twoPairsSorted.forEach { char: MutableList<Char> ->
            val sublist = char.subList(6, char.size)
            var tollerString = ""
            sublist.forEach { tollerString += it }
            completelySortedList.add(tollerString.toInt())
        }
        threesSorted.forEach { char: MutableList<Char> ->
            val sublist = char.subList(6, char.size)
            var tollerString = ""
            sublist.forEach { tollerString += it }
            completelySortedList.add(tollerString.toInt())
        }
        fullHousesSorted.forEach { char: MutableList<Char> ->
            val sublist = char.subList(6, char.size)
            var tollerString = ""
            sublist.forEach { tollerString += it }
            completelySortedList.add(tollerString.toInt())
        }
        foursSorted.forEach { char: MutableList<Char> ->
            val sublist = char.subList(6, char.size)
            var tollerString = ""
            sublist.forEach { tollerString += it }
            completelySortedList.add(tollerString.toInt())
        }
        fivesSorted.forEach { char: MutableList<Char> ->
            val sublist = char.subList(6, char.size)
            var tollerString = ""
            sublist.forEach { tollerString += it }
            completelySortedList.add(tollerString.toInt())
        }

        println(highCardsSorted)
        println(onePairsSorted)
        println(twoPairsSorted)
        println(threesSorted)
        println(fullHousesSorted)
        println(foursSorted)
        println(fivesSorted)

        // completelySortedList.reverse()
        println(completelySortedList)

        completelySortedList.forEachIndexed { index, number ->
            sum += (index + 1) * number
            println("index: ${index + 1}, number: $number")
        }

        return sum
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_1_test")
    check(part1(testInput) == 6440)
    // check(part2(testInput) == 71503)

    val input = readInput("Day07")
    part1(input).println()

    // 251.142.166 too high
    // 249.858.334 too low
    // part2(input).println()

    // check after submitting the solution
    // check(part1(input) == 32076)
    // check(part2(input) == 34278221)
}

fun isFive(input: String): Boolean {
    return input[0] == input[1] && input[1] == input[2] && input[2] == input[3] && input[3] == input[4]
}

fun isFour(input: String): Boolean {
    val validLetters = listOf('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2')
    val numbers = mutableListOf<Int>()
    validLetters.forEach { letter: Char ->
        numbers.add(input.count { it == letter })
    }
    return numbers.contains(4)
}

fun isFullHouse(input: String): Boolean {
    val validLetters = listOf('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2')
    val numbers = mutableListOf<Int>()
    validLetters.forEach { letter: Char ->
        numbers.add(input.count { it == letter })
    }
    return numbers.contains(3) && numbers.contains(2)
}

fun isThree(input: String): Boolean {
    val validLetters = listOf('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2')
    val numbers = mutableListOf<Int>()
    validLetters.forEach { letter: Char ->
        numbers.add(input.count { it == letter })
    }
    return numbers.contains(3)
}

fun isTwoPair(input: String): Boolean {
    val validLetters = listOf('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2')
    val numbers = mutableListOf<Int>()
    validLetters.forEach { letter: Char ->
        numbers.add(input.count { it == letter })
    }
    return numbers.filter { it == 2 }.size == 2
}

fun isOnePair(input: String): Boolean {
    val validLetters = listOf('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2')
    val numbers = mutableListOf<Int>()
    validLetters.forEach { letter: Char ->
        numbers.add(input.count { it == letter })
    }
    return numbers.filter { it == 2 }.size == 1
}
