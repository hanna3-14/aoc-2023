import java.io.File

fun main() {

    val visualizationPath = "visualization/day18/"

    fun part1(input: List<String>): Int {
        var edges = mutableListOf<Pair<Int, Int>>()
        var position = Pair(0, 0)
        edges.add(position)
        // find the edges
        input.forEach { line: String ->
            val direction = line.split(' ').first()
            val length = line.split(' ')[1].toInt()
            if (direction == "R") {
                repeat(length) {
                    position = position.copy(second = position.second + 1)
                    edges.add(position)
                }
            } else if (direction == "L") {
                repeat(length) {
                    position = position.copy(second = position.second - 1)
                    edges.add(position)
                }
            } else if (direction == "U") {
                repeat(length) {
                    position = position.copy(first = position.first - 1)
                    edges.add(position)
                }
            } else { // direction == "D"
                repeat(length) {
                    position = position.copy(first = position.first + 1)
                    edges.add(position)
                }
            }
        }
        edges = edges.distinct().toMutableList()

        // fill the interior
        var inner = "up"
        var interior: MutableList<Pair<Int, Int>>
        try {
            interior = fillEdges(input, edges, inner)
        } catch (e: Exception) {
            inner = "down"
            try {
                interior = fillEdges(input, edges, inner)
            } catch (e: Exception) {
                inner = "left"
                try {
                    interior = fillEdges(input, edges, inner)
                } catch (e: Exception) {
                    inner = "right"
                    interior = fillEdges(input, edges, inner)
                }
            }
        }

        // write the edges and interior into separate files for visualization
        val edgeFile = visualizationPath + "edge.txt"
        File(edgeFile).writeText(edges.toString())

        val interiorFile = visualizationPath + "interior.txt"
        File(interiorFile).writeText(interior.distinct().toString())

        return interior.distinct().size + edges.distinct().size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day18_1_test")
    check(part1(testInput) == 62)
    // check(part2(testInput) == 145)

    val input = readInput("Day18")
    part1(input).println()
    // part2(input).println()

    // check after submitting the solution
    check(part1(input) == 70253)
    // check(part2(input) == 239484)
}

fun fillUp(edges: MutableList<Pair<Int, Int>>, position: Pair<Int, Int>): MutableList<Pair<Int, Int>> {
    val additionalInterior = mutableListOf<Pair<Int, Int>>()
    var index = 1
    while (!edges.contains(Pair(position.first - index, position.second))) {
        additionalInterior.add(Pair(position.first - index, position.second))
        index += 1
        if (index > 500) {
            throw Exception()
        }
    }
    return additionalInterior
}

fun fillDown(edges: MutableList<Pair<Int, Int>>, position: Pair<Int, Int>): MutableList<Pair<Int, Int>> {
    val additionalInterior = mutableListOf<Pair<Int, Int>>()
    var index = 1
    while (!edges.contains(Pair(position.first + index, position.second))) {
        additionalInterior.add(Pair(position.first + index, position.second))
        index += 1
        if (index > 500) {
            throw Exception()
        }
    }
    return additionalInterior
}

fun fillLeft(edges: MutableList<Pair<Int, Int>>, position: Pair<Int, Int>): MutableList<Pair<Int, Int>> {
    val additionalInterior = mutableListOf<Pair<Int, Int>>()
    var index = 1
    while (!edges.contains(Pair(position.first, position.second - index))) {
        additionalInterior.add(Pair(position.first, position.second - index))
        index += 1
        if (index > 500) {
            throw Exception()
        }
    }
    return additionalInterior
}

fun fillRight(edges: MutableList<Pair<Int, Int>>, position: Pair<Int, Int>): MutableList<Pair<Int, Int>> {
    val additionalInterior = mutableListOf<Pair<Int, Int>>()
    var index = 1
    while (!edges.contains(Pair(position.first, position.second + index))) {
        additionalInterior.add(Pair(position.first, position.second + index))
        index += 1
        if (index > 500) {
            throw Exception()
        }
    }
    return additionalInterior
}

fun fillEdges(input: List<String>, edges: MutableList<Pair<Int, Int>>, inn: String): MutableList<Pair<Int, Int>> {
    var inner = inn
    var additionalInterior = mutableListOf<Pair<Int, Int>>()
    var position = Pair(0, 0)
    // input.forEach { line: String ->
    for (i in 0..input.size - 2) {
        val direction = input[i].split(' ').first()
        val nextDirection = input[i + 1].split(' ').first()
        val length = input[i].split(' ')[1].toInt()
        if (direction == "R") {
            if (inner == "up") {
                repeat(length) {
                    position = position.copy(second = position.second - 1)
                    additionalInterior.addAll(fillUp(edges, position))
                }
                if (nextDirection == "D") {
                    additionalInterior.addAll(fillRight(edges, position))
                    inner = "right"
                } else if (nextDirection == "U") {
                    inner = "left"
                }
            } else if (inner == "down") { // inner == "down"
                repeat(length) {
                    position = position.copy(second = position.second + 1)
                    additionalInterior.addAll(fillDown(edges, position))
                }
                if (nextDirection == "D") {
                    inner = "left"
                } else if (nextDirection == "U") {
                    additionalInterior.addAll(fillRight(edges, position))
                    inner = "right"
                }
            }
        } else if (direction == "L") {
            if (inner == "up") {
                repeat(length) {
                    position = position.copy(second = position.second - 1)
                    additionalInterior.addAll(fillUp(edges, position))
                }
                if (nextDirection == "D") {
                    additionalInterior.addAll(fillLeft(edges, position))
                    inner = "left"
                } else if (nextDirection == "U") {
                    inner = "right"
                }
            } else if (inner == "down") {
                repeat(length) {
                    position = position.copy(second = position.second - 1)
                    additionalInterior.addAll(fillDown(edges, position))
                }
                if (nextDirection == "D") {
                    inner = "right"
                } else if (nextDirection == "U") {
                    additionalInterior.addAll(fillLeft(edges, position))
                    inner = "left"
                }
            }
        } else if (direction == "U") {
            if (inner == "left") {
                repeat(length) {
                    position = position.copy(first = position.first - 1)
                    additionalInterior.addAll(fillLeft(edges, position))
                }
                if (nextDirection == "L") {
                    inner = "down"
                } else if (nextDirection == "R") {
                    additionalInterior.addAll(fillUp(edges, position))
                    inner = "up"
                }
            } else if (inner == "right") {
                repeat(length) {
                    position = position.copy(first = position.first - 1)
                    additionalInterior.addAll(fillRight(edges, position))
                }
                if (nextDirection == "L") {
                    additionalInterior.addAll(fillUp(edges, position))
                    inner = "up"
                } else if (nextDirection == "R") {
                    inner = "down"
                }
            }
        } else if (direction == "D") { // direction == "D"
            if (inner == "left") {
                repeat(length) {
                    position = position.copy(first = position.first + 1)
                    additionalInterior.addAll(fillLeft(edges, position))
                }
                if (nextDirection == "L") {
                    inner = "up"
                } else if (nextDirection == "R") {
                    additionalInterior.addAll(fillDown(edges, position))
                    inner = "down"
                }
            } else if (inner == "right") {
                repeat(length) {
                    position = position.copy(first = position.first + 1)
                    additionalInterior.addAll(fillRight(edges, position))
                }
                if (nextDirection == "L") {
                    additionalInterior.addAll(fillDown(edges, position))
                    inner = "down"
                } else if (nextDirection == "R") {
                    inner = "up"
                }
            }
        }

    }
    return additionalInterior
}
