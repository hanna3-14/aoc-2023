fun main() {

    fun part1(input: List<String>): Int {
        var totalSteps = 0
        val instructions = input.first().toCharArray()

        var navigations = input.subList(2, input.size).toMutableList()
        for (i in navigations.indices) {
            navigations[i] =
                navigations[i].replace(" ", "").replace("=", "").replace("(", "").replace(")", "").replace(",", "")
                    .chunked(3).toString()
        }
        var start = "AAA"
        val goal = "ZZZ"
        var index = 0
        while (start != goal) {
            totalSteps += 1
            var currentNavigation =
                navigations.filter { it[1] == start.first() && it[2] == start[1] && it[3] == start[2] }.first()
            if (instructions[index] == 'L') {
                start = currentNavigation[6].toString() + currentNavigation[7] + currentNavigation[8]
            } else {
                start = currentNavigation[11].toString() + currentNavigation[12] + currentNavigation[13]
            }
            index += 1
            if (index == instructions.size) {
                index = 0
            }
        }
        return totalSteps
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_1_test")
    val testInput2 = readInput("Day08_2_test")
    check(part1(testInput) == 2)
    check(part1(testInput2) == 6)
    // check(part2(testInput) == 30)

    val input = readInput("Day08")
    part1(input).println()
    // part2(input).println()

    // check after submitting the solution
    check(part1(input) == 15989)
    // check(part2(input) == 5923918)
}
