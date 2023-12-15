fun main() {

    fun part1(input: List<String>): Int {
        var totalsum = 0
        val strings = input.first().split(',')
        strings.forEach { line: String ->
            var hash = 0
            line.forEach { letter: Char ->
                hash += letter.code
                hash *= 17
                hash %= 256
            }
            totalsum += hash
        }
        return totalsum
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day15_1_test")
    check(part1(testInput) == 1320)

    val input = readInput("Day15")
    part1(input).println()
    // part2(input).println()

    // check after submitting the solution
    check(part1(input) == 511257)
    // check(part2(input) == 3)
}
