fun main() {

    fun part1(input: List<String>): Int {
        var sum = 0
        val roundedRocks = mutableListOf<Pair<Int, Int>>()
        input.forEachIndexed { index, s ->
            val indices = Regex("O").findAll(s).map { it.range.first }.toList()
            indices.forEach { roundedRocks.add(Pair(index, it)) }
        }
        val cubeRocks = mutableListOf<Pair<Int, Int>>()
        input.forEachIndexed { index, s ->
            val indices = Regex("#").findAll(s).map { it.range.first }.toList()
            indices.forEach { cubeRocks.add(Pair(index, it)) }
        }
        val movedRocks = mutableListOf<Pair<Int, Int>>()
        for (column in 0..<input.first().length) {
            val roundedRocksInColumn = mutableListOf<Int>()
            roundedRocks.forEach { roundedRock: Pair<Int, Int> ->
                if (roundedRock.second == column) {
                    roundedRocksInColumn.add(roundedRock.first)
                }
            }
            val cubeRocksInColumn = mutableListOf<Int>()
            cubeRocks.forEach { cubeRock: Pair<Int, Int> ->
                if (cubeRock.second == column) {
                    cubeRocksInColumn.add(cubeRock.first)
                }
            }
            cubeRocksInColumn.add(input.size)

            cubeRocksInColumn.forEachIndexed { index: Int, cubeRock: Int ->
                val numberOfRoundedRocks = roundedRocksInColumn.filter { it < cubeRock }.size
                roundedRocksInColumn.removeIf { it < cubeRock }
                for (j in 0..<numberOfRoundedRocks) {
                    if (index == 0) {
                        movedRocks.add(Pair(j, column))
                    } else {
                        movedRocks.add(Pair(cubeRocksInColumn[index - 1] + j + 1, column))
                    }
                }
            }
        }
        movedRocks.forEach { movedRock: Pair<Int, Int> ->
            sum += (input.size - movedRock.first)
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day14_1_test")
    check(part1(testInput) == 136)
    // check(part2(testInput) == 145)

    val input = readInput("Day14")
    part1(input).println()
    // part2(input).println()

    // check after submitting the solution
    check(part1(input) == 108935)
    // check(part2(input) == 239484)
}
