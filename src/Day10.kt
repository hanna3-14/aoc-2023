fun main() {

    fun part1(input: List<String>): Int {
        var startLine = 0
        var startColumn = 0
        for (i in input.indices) {
            if (input[i].contains('S')) {
                startLine = i
                startColumn = input[i].indexOf('S')
            }
        }
        val startPosition = Pair(startLine, startColumn)

        var position1 = getStartPosition1(input, startPosition)
        var position2 = getStartPosition2(input, startPosition)

        var previousPosition1 = startPosition
        var previousPosition2 = startPosition
        var counter = 1
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

    fun part2(input: List<String>): Int {
        return input.size
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_1_test")
    val testInput2 = readInput("Day10_2_test")
    check(part1(testInput) == 4)
    check(part1(testInput2) == 8)
    // check(part2(testInput) == 71503)

    val input = readInput("Day10")
    part1(input).println()
    // part2(input).println()

    // check after submitting the solution
    check(part1(input) == 6786)
    // check(part2(input) == 34278221)
}

fun getStartPosition1(input: List<String>, startPosition: Pair<Int, Int>): Pair<Int, Int> {
    return if (checkLeftIsStartPosition(input, startPosition)) {
        Pair(startPosition.first, startPosition.second - 1)
    } else if (checkDownIsStartPosition(input, startPosition)) {
        Pair(startPosition.first + 1, startPosition.second)
    } else { //(checkRightIsStartPosition()){
        Pair(startPosition.first, startPosition.second + 1)
    }
}

fun getStartPosition2(input: List<String>, startPosition: Pair<Int, Int>): Pair<Int, Int> {
    return if (checkTopIsStartPosition(input, startPosition)) {
        Pair(startPosition.first - 1, startPosition.second)
    } else if (checkRightIsStartPosition(input, startPosition)) {
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
        return if (previousPosition.second < currentPosition.second) { // if previos position is on the left
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
        return if (previousPosition.second < currentPosition.second) { // if previos position is on the left
            Pair(currentPosition.first + 1, currentPosition.second)
        } else {
            Pair(currentPosition.first, currentPosition.second - 1)
        }
    } else if (input[currentPosition.first][currentPosition.second] == 'F') {
        return if (previousPosition.first > currentPosition.first) { // if previos position is below
            Pair(currentPosition.first, currentPosition.second + 1)
        } else {
            Pair(currentPosition.first + 1, currentPosition.second)
        }
    }
    return Pair(0, 0)
}