fun main() {

    /***
     * part1:
     * find the next value for each row
     */
    fun part1(input: List<String>): Int {
        var sum = 0
        input.forEach { s: String ->
            val numbers = mutableListOf<Int>() // numbers within a row
            val stringChunks = s.split(' ')
            stringChunks.forEach { numbers.add(it.toInt()) }
            val stack = mutableListOf<Int>()

            // store the diagonal numbers on the stack
            for (i in 0..numbers.size - 2) {
                stack.add(numbers[i + 1] - numbers[i])
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
            nextNumber += numbers.last()
            sum += nextNumber
        }
        return sum
    }

    /***
     * part2:
     * find the previous number for each row
     * (I used the same algorithm but inverted the input)
     */
    fun part2(input: List<String>): Int {
        var sum = 0
        input.forEach { s: String ->
            val numbers = mutableListOf<Int>()
            val stringChunks = s.split(' ')
            stringChunks.forEach { numbers.add(it.toInt()) }
            numbers.reverse() // reverse the input to "use the same algorithm backwards"
            val stack = mutableListOf<Int>()

            // store the diagonal numbers on the stack
            for (i in 0..numbers.size - 2) {
                stack.add(numbers[i + 1] - numbers[i])
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
            nextNumber += numbers.last()
            sum += nextNumber
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_1_test")
    check(part1(testInput) == 114)
    check(part2(testInput) == 2)

    val input = readInput("Day09")
    part1(input).println()
    part2(input).println()

    // check after submitting the solution
    check(part1(input) == 1647269739)
    check(part2(input) == 864)
}
