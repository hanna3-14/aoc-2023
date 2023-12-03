fun main() {

    /***
     * part1:
     * for each line, combine the first digit and the last digit to form a single two-digit number
     * the sum of all these two-digit numbers is the solution
     */
    fun part1(input: List<String>): Int {
        var digits: String
        var solve = ""
        var sum = 0

        input.forEach { s: String -> // for each row of the input
            digits = s.filter { it.isDigit() } // remove all characters
            solve += digits.first() // concatenate the first and the last digit to a string
            solve += digits.last()
            sum += solve.toInt() // build the sum of all two-digit numbers
            solve = ""
        }
        return sum
    }

    /***
     * part2:
     * for each line, combine the first digit and the last digit to form a single two-digit number
     * the sum of all these two-digit numbers is the solution
     * some of the digits are spelled out
     */
    fun part2(input: List<String>): Int {
        var solve = ""
        var sum = 0
        val numbers = listOf(
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine",
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9"
        )
        var firstDigit: Pair<Int, String>
        var lastDigit: Pair<Int, String>

        input.forEach { s: String ->
            firstDigit = s.findAnyOf(numbers)!! // find the first occurrence of any number
            lastDigit = s.findLastAnyOf(numbers)!! // find the last occurrence of any number
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

    // check after submitting the solution
    check(part1(input) == 55488)
    check(part2(input) == 55614)
}

// converts each number string to the corresponding integer
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
