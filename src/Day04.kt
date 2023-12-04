import kotlin.math.pow

fun main() {

    fun part1(input: List<String>): Int {
        var sum = 0
        input.forEach { s: String ->
            var counter = 0
            val numbers = s.split('|')
            var winningNumbers = numbers.first().split(' ')
            winningNumbers = winningNumbers.subList(2, winningNumbers.size).filter { it != "" }
            var yourNumbers = numbers.last().split(' ')
            yourNumbers = yourNumbers.subList(1, yourNumbers.size).filter { it != "" }
            yourNumbers.forEach { number: String ->
                if (winningNumbers.contains(number)) {
                    counter += 1
                }
            }
            sum += 2.toDouble().pow((counter - 1).toDouble()).toInt()
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        val cardCounts = mutableListOf<Int>()
        input.forEach { _ -> cardCounts.add(1) }
        input.forEachIndexed { index: Int, s: String ->
            repeat(cardCounts[index]) {
                var counter = 0
                val numbers = s.split('|')
                var winningNumbers = numbers.first().split(' ')
                winningNumbers = winningNumbers.subList(2, winningNumbers.size).filter { it != "" }
                var yourNumbers = numbers.last().split(' ')
                yourNumbers = yourNumbers.subList(1, yourNumbers.size).filter { it != "" }
                yourNumbers.forEach { number: String ->
                    if (winningNumbers.contains(number)) {
                        counter += 1
                    }
                }
                for (i in index + 1..index + counter) {
                    cardCounts[i] += 1
                    counter -= 1
                }
            }
        }
        cardCounts.forEach { sum += it }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_1_test")
    check(part1(testInput) == 13)
    check(part2(testInput) == 30)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()

    // check after submitting the solution
    check(part1(input) == 23441)
    check(part2(input) == 5923918)
}
