import kotlin.math.pow

fun main() {

    /***
     * part1:
     * for each card the winning numbers are listed before the |
     * after the | are the numbers you have
     * which of the numbers you have, appear in the winning numbers?
     * first match is worth one point and each match after the first doubles the paint value of that card
     * solution: total sum of winning points
     */
    fun part1(input: List<String>): Int {
        var sum = 0

        input.forEach { s: String ->
            var counter = 0 // counts correct winning numbers
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

            // e.g. 4 winning numbers: 2³ = 8 winning points
            sum += 2.toDouble().pow((counter - 1).toDouble()).toInt()
        }
        return sum
    }

    /***
     * part2:
     * now you win additional scratch cards for each winning number that you have correct
     * e.g. if 4 winning numbers are correct you get one additional scratch card of the next 4 cards
     * solution: how many scratch cards do you end with?
     */
    fun part2(input: List<String>): Int {
        var sum = 0 // total sum of scratch cards
        val cardCounts = mutableListOf<Int>()
        // in the beginning there is one scratch card of each
        input.forEach { _ -> cardCounts.add(1) }

        input.forEachIndexed { index: Int, s: String ->
            repeat(cardCounts[index]) {
                var counter = 0 // counts correct winning numbers
                val numbers = s.split('|')
                var winningNumbers = numbers.first().split(' ')
                winningNumbers = winningNumbers.subList(2, winningNumbers.size).filter { it != "" }
                var yourNumbers = numbers.last().split(' ')
                yourNumbers = yourNumbers.subList(1, yourNumbers.size).filter { it != "" }

                // get the amount of winning numbers for each card
                yourNumbers.forEach { number: String ->
                    if (winningNumbers.contains(number)) {
                        counter += 1
                    }
                }

                // increase the amount of scratch cards according to the correct winning numbers
                for (i in index + 1..index + counter) {
                    cardCounts[i] += 1
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
