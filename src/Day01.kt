fun main() {

    fun part1(input: List<String>): Int {
        var digits: String
        var solve = ""
        var sum = 0

        input.forEach { s: String ->
            digits = s.filter { it.isDigit() }
            solve += digits.first()
            solve += digits.last()
            sum += solve.toInt()
            solve = ""
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var solve = ""
        var sum = 0
        var numbers = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "1", "2", "3", "4", "5", "6", "7", "8", "9")
        var firstDigit: Pair<Int, String>
        var lastDigit: Pair<Int, String>

        input.forEach { s: String ->
            firstDigit = s.findAnyOf(numbers)!!
            lastDigit = s.findLastAnyOf(numbers)!!
            solve += valueOfString(firstDigit.second)
            solve += valueOfString(lastDigit.second)
            sum += solve.toInt()
            solve = ""
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_1_test")
    check(part1(testInput) == 142)
    val testInput2 = readInput("Day01_2_test")
    check(part2(testInput2) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}

fun valueOfString(string: String): Int {
    return when (string) {
        "one" -> 1
        "two" -> 2
        "three" -> 3
        "four" -> 4
        "five" -> 5
        "six" -> 6
        "seven" -> 7
        "eight" -> 8
        "nine" -> 9
        else -> string.toInt()
    }
}
