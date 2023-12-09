fun main() {

    fun part1(input: List<String>): Int {

        val completelySortedList = mutableListOf<Int>()
        var sum = 0

        val customComparator = object : Comparator<String> {
            override fun compare(o1: String, o2: String): Int {
                // Custom logic for comparing letters
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

                for (i in 0..o1.length) {
                    val letter = order.getOrDefault(o2[i], -1) - order.getOrDefault(o1[i], -1)
                    if (letter != 0) {
                        return letter
                    }
                }
                return 0
            }
        }

        val fives = mutableListOf<String>()
        val fours = mutableListOf<String>()
        val fullhouses = mutableListOf<String>()
        val threes = mutableListOf<String>()
        val twoPairs = mutableListOf<String>()
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

        val highCardsSorted = highCards.sortedWith(customComparator).reversed()
        val onePairsSorted = onePairs.sortedWith(customComparator).reversed()
        val twoPairsSorted = twoPairs.sortedWith(customComparator).reversed()
        val threesSorted = threes.sortedWith(customComparator).reversed()
        val fullhousesSorted = fullhouses.sortedWith(customComparator).reversed()
        val foursSorted = fours.sortedWith(customComparator).reversed()
        val fivesSorted = fives.sortedWith(customComparator).reversed()

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

    fun part2(input: List<String>): Int {

        val completelySortedList = mutableListOf<Int>()
        var sum = 0

        val customComparator = object : Comparator<String> {
            override fun compare(o1: String, o2: String): Int {
                // Custom logic for comparing letters
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

                for (i in 0..o1.length) {
                    val letter = order.getOrDefault(o2[i], -1) - order.getOrDefault(o1[i], -1)
                    if (letter != 0) {
                        return letter
                    }
                }
                return 0
            }
        }

        val fives = mutableListOf<String>()
        val fours = mutableListOf<String>()
        val fullhouses = mutableListOf<String>()
        val threes = mutableListOf<String>()
        val twoPairs = mutableListOf<String>()
        val onePairs = mutableListOf<String>()
        val highCards = mutableListOf<String>()
        input.forEach { s: String ->
            if (!s.contains('J')) {
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
            } else {
                val categoryNumber = categorizeOneJoker(s)
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

        val highCardsSorted = highCards.sortedWith(customComparator).reversed()
        val onePairsSorted = onePairs.sortedWith(customComparator).reversed()
        val twoPairsSorted = twoPairs.sortedWith(customComparator).reversed()
        val threesSorted = threes.sortedWith(customComparator).reversed()
        val fullhousesSorted = fullhouses.sortedWith(customComparator).reversed()
        val foursSorted = fours.sortedWith(customComparator).reversed()
        val fivesSorted = fives.sortedWith(customComparator).reversed()

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

fun categorizeOneJoker(input: String): Int {
    var chunks = input.split(' ')
    val letterCountMap = mutableMapOf<Char, Int>()
    letterCountMap += mapOf('A' to chunks.first().count { it == 'A' })
    letterCountMap += mapOf('K' to chunks.first().count { it == 'K' })
    letterCountMap += mapOf('Q' to chunks.first().count { it == 'Q' })
    letterCountMap += mapOf('T' to chunks.first().count { it == 'T' })
    letterCountMap += mapOf('9' to chunks.first().count { it == '9' })
    letterCountMap += mapOf('8' to chunks.first().count { it == '8' })
    letterCountMap += mapOf('7' to chunks.first().count { it == '7' })
    letterCountMap += mapOf('6' to chunks.first().count { it == '6' })
    letterCountMap += mapOf('5' to chunks.first().count { it == '5' })
    letterCountMap += mapOf('4' to chunks.first().count { it == '4' })
    letterCountMap += mapOf('3' to chunks.first().count { it == '3' })
    letterCountMap += mapOf('2' to chunks.first().count { it == '2' })
    val maxChar = letterCountMap.maxBy { it.value }
    val lel = chunks.first().replace('J', maxChar.key)
    return when {
        isFive(lel) -> 6
        isFour(lel) -> 5
        isFullHouse(lel) -> 4
        isThree(lel) -> 3
        isTwoPair(lel) -> 2
        isOnePair(lel) -> 1
        else -> 0
    }
}