fun main() {

    fun part1(input: List<String>): Int {
        var sum = 0

        input.forEach { s: String ->
            val cleanup = s.replace("""[:;,]""".toRegex(), "")
            var fragments = cleanup.split(' ')
            val id = fragments[1]
            fragments = fragments.subList(2, fragments.size)
            var isPossible = true

            for (i in fragments.indices step 2) {
                val number = fragments[i].toInt()
                val color = fragments[i + 1]
                if ((number > 12 && color == "red") || (number > 13 && color == "green") || (number > 14 && color == "blue")) {
                    isPossible = false
                }
            }
            if (isPossible) {
                sum += id.toInt()
            }
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0

        input.forEach { s: String ->
            val cleanup = s.replace("""[:;,]""".toRegex(), "")
            var fragments = cleanup.split(' ')
            fragments = fragments.subList(2, fragments.size)
            val chunks = fragments.chunked(2)
            val map = mutableMapOf<String, Int>()

            chunks.forEach { chunk ->
                val number = chunk.first()
                val color = chunk.last()
                if ((!map.containsKey(color)) || (number.toInt() > map.get(color)!!)) {
                    map += mapOf(color to number.toInt())
                }
            }

            val product = map["red"]!! * map["green"]!! * map["blue"]!!
            sum += product
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_1_test")
    check(part1(testInput) == 8)
    check(part2(testInput) == 2286)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()

    // check after submitting the solution
    check(part1(input) == 2204)
    check(part2(input) == 71036)
}
