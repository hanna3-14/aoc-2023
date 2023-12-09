fun main() {

    fun part1(input: List<String>): Int {
        var totalSteps = 0
        val instructions = input.first()

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
            if (index == instructions.length) {
                index = 0
            }
        }
        return totalSteps
    }

    fun part2(input: List<String>): Int {
        var totalSteps = 0
        val instructions = input.first()
        println(instructions)
        var lel = mutableListOf<MutableList<Char>>()
        input.subList(2, input.size).forEach { line: String ->
            lel.add(line.toMutableList())
        }
        // println(lel)
        var navigations = mutableListOf<MutableList<String>>()
        for (i in lel.indices) {
            navigations.add(
                mutableListOf(
                    lel[i][0].toString() + lel[i][1] + lel[i][2],
                    lel[i][7].toString() + lel[i][8] + lel[i][9],
                    lel[i][12].toString() + lel[i][13] + lel[i][14]
                )
            )
        }

        val sources = mutableListOf<String>()
        navigations.forEach { sources.add(it.first()) }
        val starts = sources.filter { it[2] == 'A' }.toMutableList()

        var index = 0
        var requirement = true
        while (requirement) {
            requirement = false
            totalSteps += 1
            for (i in starts.indices) {
                var currentNavigation = navigations.first { it.first() == starts[i] }
                if (instructions[index] == 'L') {
                    starts[i] = currentNavigation[1]
                } else {
                    starts[i] = currentNavigation[2]
                }
                if (starts[i][2] != 'Z') {
                    requirement = true
                }
            }
            index += 1
            if (index == instructions.length) {
                index = 0
            }
            println(totalSteps)
        }
        println(totalSteps)
        return totalSteps
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_1_test")
    val testInput2 = readInput("Day08_2_test")
    check(part1(testInput) == 2)
    check(part1(testInput2) == 6)
    val testInput3 = readInput("Day08_3_test")
    check(part2(testInput3) == 6)

    val input = readInput("Day08")
    // part1(input).println()
    // part2(input).println()

    // check after submitting the solution
    check(part1(input) == 15989)
    // check(part2(input) == 5923918)
}
