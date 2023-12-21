fun main() {

    /***
     * part1:
     * find the loop within the input file starting at the start position (S)
     * solution: How many steps along the loop does it take to get from the starting position to the point farthest from the starting position?
     */
    fun part1(input: List<String>): Int {
        var startLine = 0
        var startColumn = 0

        // find the start position (S)
        for (i in input.indices) {
            if (input[i].contains('S')) {
                startLine = i
                startColumn = input[i].indexOf('S')
            }
        }
        val startPosition = Pair(startLine, startColumn)

        // find the 2 positions adjacent to the start position that are part of the loop
        var position1 = getStartPosition1(input, startPosition)
        var position2 = getStartPosition2(input, startPosition)

        var previousPosition1 = startPosition
        var previousPosition2 = startPosition
        var counter = 1 // counter for the steps to the farthest position

        // go along the loop in both directions until both moving positions are equal
        while (position1 != position2) {
            val nextPosition1 = getNextPosition(input, previousPosition1, position1)
            previousPosition1 = position1
            position1 = nextPosition1
            val nextPosition2 = getNextPosition(input, previousPosition2, position2)
            previousPosition2 = position2
            position2 = nextPosition2
            counter += 1
        }
        return counter
    }

    /***
     * part2:
     * find the number of tiles that are enclosed by the loop
     *
     * my algorithm:
     * go along the loop
     * for each position: add the inner points until to the next border of the loop to a list
     * e.g. for the symbol - the inner part of the loop can be "up" or "down"
     * e.g. for the symbol F I either need to add the points above and on the left, or I don't need to add anything at all
     * this depends on whether it is a 90° or 270° corner
     */
    fun part2(input: List<String>): Int {
        var startLine = 0
        var startColumn = 0

        // find the start position (S)
        for (i in input.indices) {
            if (input[i].contains('S')) {
                startLine = i
                startColumn = input[i].indexOf('S')
            }
        }
        val startPosition = Pair(startLine, startColumn)

        // this time we go along the loop but only in one direction to find the position of each loop element and the respective symbol
        val loop = mutableListOf<Pair<Int, Int>>()
        var position = getStartPosition1(input, startPosition)
        var previousPosition = startPosition
        val symbols = mutableListOf<Char>()
        symbols.add(input[position.first][position.second])
        loop.add(position)
        while (position != startPosition) {
            val nextPosition1 = getNextPosition(input, previousPosition, position)
            previousPosition = position
            position = nextPosition1
            loop.add(position)
            symbols.add(input[position.first][position.second])
        }

        // find the positions of tiles that are enclosed by the loop
        var inner = "right"
        var enclosedTiles: MutableList<Pair<Int, Int>>
        try {
            enclosedTiles = findEnclosedTiles(loop, symbols, inner)
        } catch (e: Exception) {
            inner = "down"
            try {
                enclosedTiles = findEnclosedTiles(loop, symbols, inner)
            } catch (e: Exception) {
                inner = "left"
                try {
                    enclosedTiles = findEnclosedTiles(loop, symbols, inner)
                } catch (e: Exception) {
                    inner = "up"
                    enclosedTiles = findEnclosedTiles(loop, symbols, inner)
                }
            }
        }
        return enclosedTiles.distinct().size
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_1_test")
    val testInput2 = readInput("Day10_2_test")
    check(part1(testInput) == 4)
    check(part1(testInput2) == 8)
    val testInput3 = readInput("Day10_3_test")
    val testInput4 = readInput("Day10_4_test")
    val testInput5 = readInput("Day10_5_test")
    check(part2(testInput3) == 4)
    check(part2(testInput4) == 8)
    check(part2(testInput5) == 10)

    val input = readInput("Day10")
    part1(input).println()
    part2(input).println()

    // check after submitting the solution
    check(part1(input) == 6786)
    check(part2(input) == 495)
}

fun getStartPosition1(input: List<String>, startPosition: Pair<Int, Int>): Pair<Int, Int> {
    return if (checkLeftIsStartPosition(input, startPosition)) { // left
        Pair(startPosition.first, startPosition.second - 1)
    } else if (checkDownIsStartPosition(input, startPosition)) { // down
        Pair(startPosition.first + 1, startPosition.second)
    } else { // right
        Pair(startPosition.first, startPosition.second + 1)
    }
}

fun getStartPosition2(input: List<String>, startPosition: Pair<Int, Int>): Pair<Int, Int> {
    return if (checkTopIsStartPosition(input, startPosition)) { // top
        Pair(startPosition.first - 1, startPosition.second)
    } else if (checkRightIsStartPosition(input, startPosition)) { // right
        Pair(startPosition.first, startPosition.second + 1)
    } else { // down
        Pair(startPosition.first + 1, startPosition.second)
    }
}

fun checkLeftIsStartPosition(input: List<String>, startPosition: Pair<Int, Int>): Boolean {
    return !((startPosition.second - 1 < 0)
            || (input[startPosition.first][startPosition.second - 1] == '|')
            || (input[startPosition.first][startPosition.second - 1] == 'J')
            || (input[startPosition.first][startPosition.second - 1] == '7')
            || (input[startPosition.first][startPosition.second - 1] == '.'))
}

fun checkDownIsStartPosition(input: List<String>, startPosition: Pair<Int, Int>): Boolean {
    return !((startPosition.first + 1 > input.size)
            || (input[startPosition.first + 1][startPosition.second] == '-')
            || (input[startPosition.first + 1][startPosition.second] == '7')
            || (input[startPosition.first + 1][startPosition.second] == 'F')
            || (input[startPosition.first + 1][startPosition.second] == '.'))
}

fun checkRightIsStartPosition(input: List<String>, startPosition: Pair<Int, Int>): Boolean {
    return !((startPosition.second + 1 > input.first().length)
            || (input[startPosition.first][startPosition.second + 1] == '|')
            || (input[startPosition.first][startPosition.second + 1] == 'L')
            || (input[startPosition.first][startPosition.second + 1] == 'F')
            || (input[startPosition.first][startPosition.second + 1] == '.'))
}

fun checkTopIsStartPosition(input: List<String>, startPosition: Pair<Int, Int>): Boolean {
    return !((startPosition.first - 1 < 0)
            || (input[startPosition.first - 1][startPosition.second] == '-')
            || (input[startPosition.first - 1][startPosition.second] == 'L')
            || (input[startPosition.first - 1][startPosition.second] == 'J')
            || (input[startPosition.first - 1][startPosition.second] == '.'))
}

fun getNextPosition(
    input: List<String>, previousPosition: Pair<Int, Int>, currentPosition: Pair<Int, Int>
): Pair<Int, Int> {
    if (input[currentPosition.first][currentPosition.second] == '|') {
        return if (previousPosition.first < currentPosition.first) { // if previous position is above
            Pair(currentPosition.first + 1, currentPosition.second)
        } else {
            Pair(currentPosition.first - 1, currentPosition.second)
        }
    } else if (input[currentPosition.first][currentPosition.second] == '-') {
        return if (previousPosition.second < currentPosition.second) { // if previous position is on the left
            Pair(currentPosition.first, currentPosition.second + 1)
        } else {
            Pair(currentPosition.first, currentPosition.second - 1)
        }
    } else if (input[currentPosition.first][currentPosition.second] == 'L') {
        return if (previousPosition.first < currentPosition.first) { // if previous position is above
            Pair(currentPosition.first, currentPosition.second + 1)
        } else {
            Pair(currentPosition.first - 1, currentPosition.second)
        }
    } else if (input[currentPosition.first][currentPosition.second] == 'J') {
        return if (previousPosition.first < currentPosition.first) { // if previous position is above
            Pair(currentPosition.first, currentPosition.second - 1)
        } else {
            Pair(currentPosition.first - 1, currentPosition.second)
        }
    } else if (input[currentPosition.first][currentPosition.second] == '7') {
        return if (previousPosition.second < currentPosition.second) { // if previous position is on the left
            Pair(currentPosition.first + 1, currentPosition.second)
        } else {
            Pair(currentPosition.first, currentPosition.second - 1)
        }
    } else if (input[currentPosition.first][currentPosition.second] == 'F') {
        return if (previousPosition.first > currentPosition.first) { // if previous position is below
            Pair(currentPosition.first, currentPosition.second + 1)
        } else {
            Pair(currentPosition.first + 1, currentPosition.second)
        }
    }
    return Pair(0, 0)
}

fun fillUpRight(
    loop: MutableList<Pair<Int, Int>>,
    position: Pair<Int, Int>,
): MutableList<Pair<Int, Int>> {
    val innerTiles = mutableListOf<Pair<Int, Int>>()
    var index = 1
    while (!loop.contains(
            Pair(
                position.first, position.second + index
            )
        )
    ) {
        innerTiles.add(Pair(position.first, position.second + index))
        index += 1
        if (index > 140) {
            throw Exception()
        }
    }
    return innerTiles
}

fun fillUpLeft(
    loop: MutableList<Pair<Int, Int>>,
    position: Pair<Int, Int>,
): MutableList<Pair<Int, Int>> {
    val innerTiles = mutableListOf<Pair<Int, Int>>()
    var index = 1
    while (!loop.contains(
            Pair(
                position.first, position.second - index
            )
        )
    ) {
        innerTiles.add(Pair(position.first, position.second - index))
        index += 1
        if (index > 140) {
            throw Exception()
        }
    }
    return innerTiles
}

fun fillUpUp(
    loop: MutableList<Pair<Int, Int>>,
    position: Pair<Int, Int>,
): MutableList<Pair<Int, Int>> {
    val innerTiles = mutableListOf<Pair<Int, Int>>()
    var index = 1
    while (!loop.contains(
            Pair(
                position.first - index, position.second
            )
        )
    ) {
        innerTiles.add(Pair(position.first - index, position.second))
        index += 1
        if (index > 140) {
            throw Exception()
        }
    }
    return innerTiles
}

fun fillUpDown(
    loop: MutableList<Pair<Int, Int>>,
    position: Pair<Int, Int>,
): MutableList<Pair<Int, Int>> {
    val innerTiles = mutableListOf<Pair<Int, Int>>()
    var index = 1
    while (!loop.contains(
            Pair(
                position.first + index, position.second
            )
        )
    ) {
        innerTiles.add(Pair(position.first + index, position.second))
        index += 1
        if (index > 140) {
            throw Exception()
        }
    }
    return innerTiles
}

fun moveInnerByAnRightAngle(inner: String): String {
    return when (inner) {
        "left" -> "up"
        "right" -> "down"
        "up" -> "left"
        "down" -> "right"
        else -> ""
    }
}

fun moveInnerByALeftAngle(inner: String): String {
    return when (inner) {
        "left" -> "down"
        "right" -> "up"
        "up" -> "right"
        "down" -> "left"
        else -> ""
    }
}

fun findEnclosedTiles(
    loop: MutableList<Pair<Int, Int>>,
    symbols: MutableList<Char>,
    _inner: String
): MutableList<Pair<Int, Int>> {
    var inner = _inner
    val enclosedTiles = mutableListOf<Pair<Int, Int>>()
    for (i in symbols.indices) {
        val position = loop[i]
        val symbol = symbols[i]
        if (symbol == '|') {
            if (inner == "left") {
                try {
                    fillUpLeft(loop, position).forEach { enclosedTiles.add(it) }
                } catch (e: Exception) {
                    throw Exception()
                }
            } else { // inner == "right"
                try {
                    fillUpRight(loop, position).forEach { enclosedTiles.add(it) }
                } catch (e: Exception) {
                    throw Exception()
                }
            }
        } else if (symbol == '-') {
            if (inner == "up") {
                try {
                    fillUpUp(loop, position).forEach { enclosedTiles.add(it) }
                } catch (e: Exception) {
                    throw Exception()
                }
            } else { // inner == "down"
                try {
                    fillUpDown(loop, position).forEach { enclosedTiles.add(it) }
                } catch (e: Exception) {
                    throw Exception()
                }
            }
        } else if (symbol == 'L') {
            if (inner == "left" || inner == "down") {
                try {
                    fillUpLeft(loop, position).forEach { enclosedTiles.add(it) }
                } catch (e: Exception) {
                    throw Exception()
                }
                try {
                    fillUpDown(loop, position).forEach { enclosedTiles.add(it) }
                } catch (e: Exception) {
                    throw Exception()
                }
            }
            inner = moveInnerByALeftAngle(inner) // L or 7
        } else if (symbol == 'J') {
            if (inner == "down" || inner == "right") {
                try {
                    fillUpDown(loop, position).forEach { enclosedTiles.add(it) }
                } catch (e: Exception) {
                    throw Exception()
                }
                try {
                    fillUpRight(loop, position).forEach { enclosedTiles.add(it) }
                } catch (e: Exception) {
                    throw Exception()
                }
            }
            inner = moveInnerByAnRightAngle(inner) // F or J
        } else if (symbol == '7') {
            if (inner == "right" || inner == "up") {
                try {
                    fillUpRight(loop, position).forEach { enclosedTiles.add(it) }
                } catch (e: Exception) {
                    throw Exception()
                }
                try {
                    fillUpUp(loop, position).forEach { enclosedTiles.add(it) }
                } catch (e: Exception) {
                    throw Exception()
                }
            }
            inner = moveInnerByALeftAngle(inner) // L or 7
        } else if (symbol == 'F') {
            if (inner == "up" || inner == "left") {
                try {
                    fillUpUp(loop, position).forEach { enclosedTiles.add(it) }
                } catch (e: Exception) {
                    throw Exception()
                }
                try {
                    fillUpLeft(loop, position).forEach { enclosedTiles.add(it) }
                } catch (e: Exception) {
                    throw Exception()
                }

            }
            inner = moveInnerByAnRightAngle(inner) // F or J
        }
    }
    return enclosedTiles
}
