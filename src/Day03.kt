fun main() {

    /***
     * part1:
     * sum up any number that is adjacent, even diagonally, to a symbol (other than .)
     */
    fun part1(input: List<String>): Int {
        var sum = 0
        val totalNumbersRoundSymbols = mutableListOf<Int>() // list of all the numbers adjacent to any symbol
        var symbolsPerRow: MutableList<Int> // positions of symbols within a single row
        val symbols = mutableListOf<MutableList<Int>>() // positions of symbols for any row

        // find the positions of the symbols
        input.forEach { s: String ->
            symbolsPerRow = Regex("""[^0-9.]""").findAll(s).map { it.range.first }.toMutableList()
            symbols.add(symbolsPerRow)
        }

        symbols.forEachIndexed { row, _ ->
            if (symbols[row] != emptyList<Int>()) {
                symbols[row].forEach { column: Int ->
                    val numbersRoundSpecificSymbol = findNumbersRoundSymbol(input, row, column)
                    numbersRoundSpecificSymbol.forEach { totalNumbersRoundSymbols.add(it) }
                }
            }
        }

        totalNumbersRoundSymbols.forEach { number: Int ->
            sum += number
        }
        return sum
    }

    /***
     * part2:
     * find any asterisk that has 2 adjacent numbers
     * multiply the values of these two numbers
     * the solution is the sum of all these products
     */
    fun part2(input: List<String>): Int {
        var sum = 0
        val totalNumbersRoundSymbols = mutableListOf<Int>()

        var asterisksPerRow: MutableList<Int>
        val asterisks = mutableListOf<MutableList<Int>>()

        input.forEach { s: String ->
            asterisksPerRow = Regex("""[*]""").findAll(s).map { it.range.first }.toMutableList()
            asterisks.add(asterisksPerRow)
        }

        asterisks.forEachIndexed { row, _ ->
            if (asterisks[row] != emptyList<Int>()) {
                asterisks[row].forEach { column: Int ->
                    val numbersRoundSpecificSymbol = findNumbersRoundSymbol(input, row, column)
                    numbersRoundSpecificSymbol.forEach { totalNumbersRoundSymbols.add(it) }
                    if (numbersRoundSpecificSymbol.size == 2) {
                        sum += numbersRoundSpecificSymbol[0] * numbersRoundSpecificSymbol[1]
                    }
                }
            }
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_1_test")
    check(part1(testInput) == 4361)
    check(part2(testInput) == 467835)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()

    // check after submitting the solution
    check(part1(input) == 539713)
    check(part2(input) == 84159075)
}

// finds all the numbers that are adjacent to a symbol
fun findNumbersRoundSymbol(input: List<String>, row: Int, column: Int): MutableList<Int> {
    val numbers = mutableListOf<Int>() // contains all numbers adjacent to the symbol

    if (input[row - 1][column].isDigit()) { // if number is directly above the symbol
        numbers.add(findNumberAtPosition(input, row - 1, column))
    } else {
        if (input[row - 1][column - 1].isDigit()) { // number is above on the left side
            numbers.add(findNumberAtPosition(input, row - 1, column - 1))
        }
        if (input[row - 1][column + 1].isDigit()) { // number is above on the right side
            numbers.add(findNumberAtPosition(input, row - 1, column + 1))
        }
    }
    if (input[row][column - 1].isDigit()) { // number is in the same row on the left
        numbers.add(findNumberAtPosition(input, row, column - 1))
    }
    if (input[row][column + 1].isDigit()) { // number is in the same row on the right
        numbers.add(findNumberAtPosition(input, row, column + 1))
    }
    if (input[row + 1][column].isDigit()) { // if number is directly below the symbol
        numbers.add(findNumberAtPosition(input, row + 1, column))
    } else {
        if (input[row + 1][column - 1].isDigit()) { // number is below on the left side
            numbers.add(findNumberAtPosition(input, row + 1, column - 1))
        }
        if (input[row + 1][column + 1].isDigit()) { // number is below on the right side
            numbers.add(findNumberAtPosition(input, row + 1, column + 1))
        }
    }
    return numbers
}

// find a number at specific position adjacent to the symbol
fun findNumberAtPosition(input: List<String>, row: Int, column: Int): Int {
    var number = ""
    if (input[row][column].isDigit()) {
        var start = column
        while (input[row][start].isDigit()) {
            start -= 1
            if (start == -1) {
                break
            }
        }
        start += 1
        var end = column
        while (input[row][end].isDigit()) {
            end += 1
            if (end == input.size) {
                break
            }
        }
        end -= 1
        for (i in start..end) {
            number += input[row][i]
        }
    }
    return number.toInt()
}
