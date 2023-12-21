fun main() {

    /***
     * part1:
     * each race has a time limit
     * how many possibilities are there to beat the record?
     * solution: multiply this number for all races
     */
    fun part1(input: List<String>): Int {
        var total = 1
        var times = input.first().split(' ')
        times = times.subList(1, times.size).filter { it != "" }
        var distances = input.last().split(' ')
        distances = distances.subList(1, distances.size).filter { it != "" }

        for (i in times.indices) {
            var wins = 0
            for (buttonTime in 0..times[i].toInt()) {
                val distance = (times[i].toInt() - buttonTime) * buttonTime
                if (distance > distances[i].toInt()) {
                    wins += 1
                }
            }
            total *= wins
        }
        return total
    }

    /***
     * part2:
     * actually it is only one large race
     */
    fun part2(input: List<String>): Int {
        var times = input.first().split(' ')
        times = times.subList(1, times.size).filter { it != "" }
        var totalTime = ""
        times.forEach { totalTime += it }
        val totalTimeLong = totalTime.toLong()
        var distances = input.last().split(' ')
        distances = distances.subList(1, distances.size).filter { it != "" }
        var totalDistance = ""
        distances.forEach { totalDistance += it }
        val totalDistanceLong = totalDistance.toLong()

        var wins = 0
        for (buttonTime in 0..totalTimeLong) {
            val distance = (totalTimeLong - buttonTime) * buttonTime
            if (distance > totalDistanceLong) {
                wins += 1
            }
        }
        return wins
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_1_test")
    check(part1(testInput) == 288)
    check(part2(testInput) == 71503)

    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()

    // check after submitting the solution
    check(part1(input) == 32076)
    check(part2(input) == 34278221)
}
