fun main() {

    fun part1(input: List<String>): Int {
        var sum = 0
        input.forEach { s: String ->
            val chunks = mutableListOf<Int>()
            val stringChunks = s.split(' ')
            stringChunks.forEach { chunks.add(it.toInt()) }
            val stack = mutableListOf<Int>()
            for (i in 0..chunks.size - 2) {
                stack.add(chunks[i + 1] - chunks[i])
                repeat(i) {
                    stack.add(stack.last() - stack[0])
                    stack.removeFirst()
                }
            }
            stack.removeLast()
            var nextNumber = 0
            for (i in stack.size - 1 downTo 0) {
                nextNumber += stack[i]
            }
            nextNumber += chunks.last()
            sum += nextNumber
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_1_test")
    check(part1(testInput) == 114)
    // check(part2(testInput) == 71503)

    val input = readInput("Day09")
    part1(input).println()
    // part2(input).println()

    // check after submitting the solution
    check(part1(input) == 1647269739)
    // check(part2(input) == 34278221)
}
