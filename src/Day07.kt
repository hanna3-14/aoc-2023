fun main() {

    fun part1(input: List<String>): Int {

        var completelySortedList = mutableListOf<Int>()
        var sum = 0

        val customComparator = object : Comparator<String> {
            override fun compare(o1: String, o2: String): Int {
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

        println(highCards)
        println(onePairs)
        println(twoPairs)
        println(threes)
        println(fullhouses)
        println(fours)
        println(fives)

        val highCardsSorted = highCards.sortedWith(customComparator).reversed()
        val onePairsSorted = onePairs.sortedWith(customComparator).reversed()
        val twoPairsSorted = twoPairs.sortedWith(customComparator).reversed()
        val threesSorted = threes.sortedWith(customComparator).reversed()
        val fullhousesSorted = fullhouses.sortedWith(customComparator).reversed()
        val foursSorted = fours.sortedWith(customComparator).reversed()
        val fivesSorted = fives.sortedWith(customComparator).reversed()

        println(highCardsSorted)
        println(onePairsSorted)
        println(twoPairsSorted)
        println(threesSorted)
        println(fullhousesSorted)
        println(foursSorted)
        println(fivesSorted)

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

        println(completelySortedList)

        completelySortedList.forEachIndexed { index, number ->
            sum += (index + 1) * number
            // println("index: ${index + 1}, number: $number")
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
    // part1(input).println()

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
