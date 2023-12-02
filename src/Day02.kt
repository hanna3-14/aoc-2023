fun main() {

    fun part1(input: List<String>): Int {
        var sum = 0

        input.forEach { s: String ->
            var cleanup = s.replace("""[:;,]""".toRegex(), "")
            var fragments = cleanup.split(' ')
            var id = fragments[1]
            fragments = fragments.subList(2, fragments.size)
            var isPossible = true

            for (i in 0..fragments.size - 1 step 2) {
                var number = fragments[i].toInt()
                var color = fragments[i + 1]
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
            var cleanup = s.replace("""[:;,]""".toRegex(), "")
            var fragments = cleanup.split(' ')
            fragments = fragments.subList(2, fragments.size)
            val chunks = fragments.chunked(2)
            var map = mapOf<String, Int>()

            chunks.forEach { chunk ->
                var number = chunk.first()
                var color = chunk.last()
                if ((!map.containsKey(color)) || (number.toInt() > map.get(color)!!)) {
                    map += mapOf(color to number.toInt())
                }
            }

            var product = map.get("red")!! * map.get("green")!! * map.get("blue")!!
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
}
