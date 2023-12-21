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
        var startLine = 0
        var startColumn = 0
        for (i in input.indices) {
            if (input[i].contains('S')) {
                startLine = i
                startColumn = input[i].indexOf('S')
            }
        }
        val startPosition = Pair(startLine, startColumn)

        var position = getStartPosition1(input, startPosition)

        var previousPosition = startPosition
        val snake = mutableListOf<Pair<Int, Int>>()
        // snake.add(startPosition)
        snake.add(position)
        val symbols = mutableListOf<Char>()
        // symbols.add(input[startPosition.first][startPosition.second])
        symbols.add(input[position.first][position.second])
        while (position != startPosition) {
            val nextPosition1 = getNextPosition(input, previousPosition, position)
            previousPosition = position
            position = nextPosition1
            snake.add(position)
            symbols.add(input[position.first][position.second])
        }
        println("snake = $snake")

        // ausmalen
        val totalInner = mutableListOf<Pair<Int, Int>>()
        var currentPosition = startPosition
        var innerSnake = mutableListOf<Pair<Int, Int>>()
        var direction = "R"

        /*snake.forEach { position: Pair<Int, Int> ->
            if(direction == "R"){
                while()
            }
        }*/

        //snake.forEach { symbols.add(input[it.first][it.second]) }
        var inner = "left"
        println("symbols: $symbols")
        //symbols.forEach { symbol: Char ->
        for (i in 0..symbols.size - 2) {
            position = snake[i]
            innerSnake.clear()
            var nextSymbol = symbols[i + 1]
            if ((symbols[i] == '|') || (symbols[i] == 'F') || (symbols[i] == 'J')) {
                if (inner == "right") {
                    var index = 1
                    while (!snake.contains(
                            Pair(
                                position.first,
                                position.second + index
                            )
                        )
                    ) {
                        innerSnake.add(Pair(position.first, position.second + index))
                        index += 1
                    }
                } else if (inner == "left") {
                    var index = 1
                    while (!snake.contains(
                            Pair(
                                position.first,
                                position.second - index
                            )
                        )
                    ) {
                        innerSnake.add(Pair(position.first, position.second - index))
                        index += 1
                    }
                }
                if (inner == "right") {
                    if (nextSymbol == 'L') {
                        inner = "up"
                    }
                    if (nextSymbol == 'F') {
                        inner = "down"
                    }
                } else {
                    if (nextSymbol == 'L') {
                        inner = "down"
                    }
                    if (nextSymbol == 'F') {
                        inner = "up"
                    }
                }
            } else if (symbols[i] == '-') {
                if (inner == "up") {
                    var index = 1
                    while (!snake.contains(
                            Pair(
                                position.first - index,
                                position.second
                            )
                        )
                    ) {
                        innerSnake.add(Pair(position.first - index, position.second))
                        index += 1
                    }
                } else if (inner == "down") {
                    var index = 1
                    while (!snake.contains(
                            Pair(
                                position.first + index,
                                position.second
                            )
                        )
                    ) {
                        innerSnake.add(Pair(position.first + index, position.second))
                        index += 1
                    }
                }
                if (inner == "up") {
                    if (nextSymbol == 'J') {
                        inner = "left"
                    }
                    if (nextSymbol == '7') {
                        inner = "right"
                    }
                } else {
                    if (nextSymbol == 'J') {
                        inner = "right"
                    }
                    if (nextSymbol == '7') {
                        inner = "left"
                    }
                }
            }
            innerSnake.forEach { totalInner.add(it) }
        }
        println(totalInner.distinct())
        println(totalInner.distinct().size)
        return totalInner.distinct().size
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_1_test")
    val testInput2 = readInput("Day10_2_test")
    check(part1(testInput) == 4)
    check(part1(testInput2) == 8)
    val testInput3 = readInput("Day10_3_test")
    val testInput4 = readInput("Day10_4_test")
    val testInput5 = readInput("Day10_5_test")
    // check(part2(testInput3) == 4) // inner == "right"
    check(part2(testInput4) == 8) // inner == "left"
    // check(part2(testInput5) == 10)

    val input = readInput("Day10")
    // part1(input).println()
    // part2(input).println()

    // check after submitting the solution
    // check(part1(input) == 6786)
    // check(part2(input) == 34278221)
}
