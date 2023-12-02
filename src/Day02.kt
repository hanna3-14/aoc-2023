fun main() {

    fun part1(input: List<String>): Int {
        var sum = 0

        input.forEach { s: String ->
            var cleanup = s.replace("""[:;,]""".toRegex(), "")
            var fragments = cleanup.split(' ')
            var id = fragments[1]
            fragments = fragments.subList(2, fragments.size)
            var isPossible = true

            for (i in 0..fragments.size - 1 step 2) {
                var number = fragments[i].toInt()
                var color = fragments[i + 1]
                if ((number > 12 && color == "red") || (number > 13 && color == "green") || (number > 14 && color == "blue")) {
                    isPossible = false
                }
            }
            if (isPossible) {
                sum += id.toInt()
            }
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_1_test")
    check(part1(testInput) == 8)
    // val testInput2 = readInput("Day02_2_test")
    // check(part2(testInput2) == 281)

    val input = readInput("Day02")
    part1(input).println()
    // part2(input).println()
}
