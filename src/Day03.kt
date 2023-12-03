fun main() {

    fun part1(input: List<String>): Int {
        var sum = 0
        var numbers = mutableListOf<Int>()

        var symbolsPerRow: MutableList<Int>
        val symbols = mutableListOf<MutableList<Int>>()

        input.forEach { s: String ->
            symbolsPerRow = Regex("""[^0-9.]""").findAll(s).map { it.range.first }.toMutableList()
            symbols.add(symbolsPerRow)
        }

        for (row in symbols.indices) {
            if (symbols[row] != emptyList<Int>()) {
                symbols[row].forEach { column: Int ->
                    var lel = findNumber(input, row, column)
                    lel.forEach { numbers.add(it) }
                }
            }
        }

        numbers.forEach { number: Int ->
            sum += number
        }

        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_1_test")
    check(part1(testInput) == 4361)

    val input = readInput("Day03")
    part1(input).println()
}

fun findNumber(
    input: List<String>, row: Int, column: Int
): MutableList<Int> {
    var numbers = mutableListOf<Int>()
    if (input[row - 1][column].isDigit()) {  // if number is directly above the symbol
        var start = column
        while (input[row - 1][start].isDigit()) {
            start -= 1
        }
        start += 1
        var end = column
        while (input[row - 1][end].isDigit()) {
            end += 1
        }
        end -= 1
        var number = ""
        for (i in start..end) {
            number += input[row - 1][i]
        }
        numbers.add(number.toInt())
    } else {
        if (input[row - 1][column - 1].isDigit()) { // number is above on the left side
            var start = column - 1
            while (input[row - 1][start].isDigit()) {
                start -= 1
                if (start == -1) {
                    break
                }
            }
            start += 1
            var end = column - 1
            while (input[row - 1][end].isDigit()) {
                end += 1
            }
            end -= 1
            var number = ""
            for (i in start..end) {
                number += input[row - 1][i]
            }
            numbers.add(number.toInt())
        }
        if (input[row - 1][column + 1].isDigit()) { // number is above on the right side
            var start = column + 1
            while (input[row - 1][start].isDigit()) {
                start -= 1
            }
            start += 1
            var end = column + 1
            while (input[row - 1][end].isDigit()) {
                end += 1
                if (end == input.size + 1) {
                    break
                }
            }
            end -= 1
            var number = ""
            for (i in start..end) {
                number += input[row - 1][i]
            }
            numbers.add(number.toInt())
        }
    }
    if (input[row][column - 1].isDigit()) { // number is in the same row on the left
        var start = column - 1
        while (input[row][start].isDigit()) {
            start -= 1
            if (start == -1) {
                break
            }
        }
        start += 1
        var end = column - 1
        var number = ""
        for (i in start..end) {
            number += input[row][i]
        }
        numbers.add(number.toInt())
    }
    if (input[row][column + 1].isDigit()) { // number is in the same row on the right
        var start = column + 1
        var end = column + 1
        while (input[row][end].isDigit()) {
            end += 1
            if (end == input.size) {
                break
            }
        }
        end -= 1
        var number = ""
        for (i in start..end) {
            number += input[row][i]
        }
        numbers.add(number.toInt())
    }
    if (input[row + 1][column].isDigit()) { // if number is directly below the symbol
        var start = column
        while (input[row + 1][start].isDigit()) {
            start -= 1
        }
        start += 1
        var end = column
        while (input[row + 1][end].isDigit()) {
            end += 1
        }
        end -= 1
        var number = ""
        for (i in start..end) {
            number += input[row + 1][i]
        }
        numbers.add(number.toInt())
    } else {
        if (input[row + 1][column - 1].isDigit()) { // number is below on the left side
            var start = column - 1
            while (input[row + 1][start].isDigit()) {
                start -= 1
                if (start == -1) {
                    break
                }
            }
            start += 1
            var end = column - 1
            while (input[row + 1][end].isDigit()) {
                end += 1
            }
            end -= 1
            var number = ""
            for (i in start..end) {
                number += input[row + 1][i]
            }
            numbers.add(number.toInt())
        }
        if (input[row + 1][column + 1].isDigit()) { // number is below on the right side
            var start = column + 1
            while (input[row + 1][start].isDigit()) {
                start -= 1
            }
            start += 1
            var end = column + 1
            while (input[row + 1][end].isDigit()) {
                end += 1
                if (end == input.size) {
                    break
                }
            }
            end -= 1
            var number = ""
            for (i in start..end) {
                number += input[row + 1][i]
            }
            numbers.add(number.toInt())
        }
    }
    return numbers
}
