import kotlin.math.abs

fun main() {

    /***
     * part1:
     * the input consists of empty space (.) and galaxies (#)
     * 1. expand the universe (for each row / column that is empty, another empty row / column is added)
     * 2. calculate the shorted paths between every pair of galaxies
     * solution: the sum of all distances between the pairs of galaxies
     */
    fun part1(input: List<String>): Int {
        // find the positions of each galaxy before the expansion of the universe
        val galaxies = mutableListOf<Pair<Int, Int>>()
        input.forEachIndexed { index, s ->
            if (s.contains('#')) {
                val indices = Regex("#").findAll(s).map { it.range.first }.toList()
                indices.forEach { galaxies.add(Pair(index, it)) }
            }
        }

        // find the positions of each galaxy after the expansion of the universe
        val newGalaxies = mutableListOf<Pair<Int, Int>>()

        // expand space from top to bottom
        val emptyLines = mutableListOf<Int>()
        for (i in galaxies.indices) {
            val line = galaxies.filter { it.first == i }
            if (line.isEmpty()) {
                emptyLines.add(i)
            }
        }

        var offset = 0
        for (line in emptyLines) {
            for (galaxy in galaxies) {
                if (line + offset < galaxy.first) {
                    val gal = galaxy.copy(first = galaxy.first + 1)
                    newGalaxies.add(gal)
                }
            }
            galaxies.removeIf { line + offset < it.first }
            newGalaxies.forEach { galaxies.add(it) }
            newGalaxies.clear()
            offset += 1
        }

        // expand space from left to right
        val emptyColumns = mutableListOf<Int>()
        for (i in galaxies.indices) {
            val column = galaxies.filter { it.second == i }
            if (column.isEmpty()) {
                emptyColumns.add(i)
            }
        }

        offset = 0
        for (column in emptyColumns) {
            for (galaxy in galaxies) {
                if (column + offset < galaxy.second) {
                    val gal = galaxy.copy(second = galaxy.second + 1)
                    newGalaxies.add(gal)
                }
            }
            galaxies.removeIf { column + offset < it.second }
            newGalaxies.forEach { galaxies.add(it) }
            newGalaxies.clear()
            offset += 1
        }

        // calculate shorted paths between all galaxies
        var totalDistance = 0
        for (i in galaxies.size - 1 downTo 0) {
            for (j in galaxies.size - 2 downTo 0) {
                var distance = 0
                distance += abs(galaxies[j].first - galaxies[i].first)
                distance += abs(galaxies[j].second - galaxies[i].second)
                totalDistance += distance
            }
            galaxies.remove(galaxies[i])
        }

        return totalDistance
    }

    fun part2(input: List<String>, expansion: Int): Long {

        // calculate the correct expansion value
        val exp = expansion - 1

        // find the positions of each galaxy before the expansion of the universe
        val galaxies = mutableListOf<Pair<Long, Long>>()
        input.forEachIndexed { index, s ->
            if (s.contains('#')) {
                val indices = Regex("#").findAll(s).map { it.range.first }.toList()
                indices.forEach { galaxies.add(Pair(index.toLong(), it.toLong())) }
            }
        }

        // find the positions of each galaxy after the expansion of the universe
        val newGalaxies = mutableListOf<Pair<Long, Long>>()

        // expand space from top to bottom
        val emptyLines = mutableListOf<Int>()
        for (i in galaxies.indices) {
            val line = galaxies.filter { it.first == i.toLong() }
            if (line.isEmpty()) {
                emptyLines.add(i)
            }
        }

        var offset = 0
        for (line in emptyLines) {
            for (galaxy in galaxies) {
                if (line + offset < galaxy.first) {
                    val gal = galaxy.copy(first = galaxy.first + exp)
                    newGalaxies.add(gal)
                }
            }
            galaxies.removeIf { line + offset < it.first }
            newGalaxies.forEach { galaxies.add(it) }
            newGalaxies.clear()
            offset += exp
        }

        // expand space from left to right
        val emptyColumns = mutableListOf<Int>()
        for (i in galaxies.indices) {
            val column = galaxies.filter { it.second == i.toLong() }
            if (column.isEmpty()) {
                emptyColumns.add(i)
            }
        }

        offset = 0
        for (column in emptyColumns) {
            for (galaxy in galaxies) {
                if (column + offset < galaxy.second) {
                    val gal = galaxy.copy(second = galaxy.second + exp)
                    newGalaxies.add(gal)
                }
            }
            galaxies.removeIf { column + offset < it.second }
            newGalaxies.forEach { galaxies.add(it) }
            newGalaxies.clear()
            offset += exp
        }

        // calculate shorted paths between all galaxies
        var totalDistance = 0.toLong()
        for (i in galaxies.size - 1 downTo 0) {
            for (j in galaxies.size - 2 downTo 0) {
                var distance = 0.toLong()
                distance += abs(galaxies[j].first - galaxies[i].first)
                distance += abs(galaxies[j].second - galaxies[i].second)
                totalDistance += distance
            }
            galaxies.remove(galaxies[i])
        }

        return totalDistance
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day11_1_test")
    check(part1(testInput) == 374)
    check(part2(testInput, 10) == 1030.toLong())
    check(part2(testInput, 100) == 8410.toLong())

    val input = readInput("Day11")
    part1(input).println()
    part2(input, 1000000).println()

    // check after submitting the solution
    check(part1(input) == 9550717)
    check(part2(input, 1000000) == 648458253817)
}
