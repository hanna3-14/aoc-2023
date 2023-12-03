fun main() {

    /***
     * part1:
     * each line represents one game which consists of several sets of cubes
     * a game is impossible if none of the sets exceeds the maximum for any color
     * the maximum for the colors are:
     * 12 red
     * 13 green
     * 14 blue
     * the solution is the sum of the ids of the possible games
     */
    fun part1(input: List<String>): Int {
        var sum = 0

        input.forEach { s: String ->
            val cleanup = s.replace("""[:;,]""".toRegex(), "") // remove unnecessary symbols
            var fragments = cleanup.split(' ') // split the string into a list
            val id = fragments[1]
            fragments = fragments.subList(2, fragments.size) // first 2 entries are not needed anymore
            var isPossible = true

            for (i in fragments.indices step 2) {
                // e.g. fragments = [3, blue, 4, red, 1, red, 2, green, 6, blue, 2, green]
                val number = fragments[i].toInt()
                val color = fragments[i + 1]
                // if any number is too large based on the respective color, the game is impossible
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

    /***
     * part1:
     * each line represents one game which consists of several sets of cubes
     * find the minimum of cubes that have to be in the bag for each game
     * for each game multiply the amount of red, green and blue cubes
     * the solution is the sum of the above-mentioned products
     */
    fun part2(input: List<String>): Int {
        var sum = 0

        input.forEach { s: String ->
            val cleanup = s.replace("""[:;,]""".toRegex(), "")
            var fragments = cleanup.split(' ')
            fragments = fragments.subList(2, fragments.size)
            val chunks = fragments.chunked(2)
            val map = mutableMapOf<String, Int>()

            chunks.forEach { chunk ->
                // e.g. chunk = [[3, blue], [4, red], [1, red], [2, green], [6, blue], [2, green]]
                val number = chunk.first()
                val color = chunk.last()
                // add the biggest number for each color to a map
                if (!map.containsKey(color) || (number.toInt() > map[color]!!)) {
                    map += mapOf(color to number.toInt())
                }
            }

            // e.g. map = {blue=6, red=4, green=2}
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
