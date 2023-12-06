fun main() {

    fun part1(input: List<String>): Int {
        var total = 1
        var times = input[0].split(' ')
        times = times.subList(1, times.size).filter { it != "" }
        var distances = input[1].split(' ')
        distances = distances.subList(1, distances.size).filter { it != "" }
        for (i in times.indices) {
            var wins = 0
            for (buttonTime in 0..times[i].toInt()) {
                var distance = (times[i].toInt() - buttonTime) * buttonTime
                if (distance > distances[i].toInt()) {
                    wins += 1
                }
            }
            total *= wins
        }
        return total
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_1_test")
    check(part1(testInput) == 288)
    // check(part2(testInput) == 30)

    val input = readInput("Day06")
    part1(input).println()
    // part2(input).println()

    // check after submitting the solution
    check(part1(input) == 32076)
    // check(part2(input) == 5923918)
}
