fun main() {

    fun part1(input: List<String>): Int {
        var digits: String
        var solve = ""
        var sum = 0

        input.forEach { s: String ->
            digits = s.filter { it.isDigit() }
            solve += digits.first()
            solve += digits.last()
            sum += solve.toInt()
            solve = ""
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var digits = emptyArray<String>()
        var solve = ""
        var sum = 0
        var numbers = listOf("one", "two","three","four","five","six","seven","eight","nine", "1", "2", "3", "4", "5", "6", "7", "8", "9")
        var haha: Pair<Int, String>
        var lel: Pair<Int, String>

        for (i in input){
            haha = i.findAnyOf(numbers)!!
            lel = i.findLastAnyOf(numbers)!!
            when(haha.second){
                "one", "1" -> solve += 1
                "two", "2" -> solve += 2
                "three", "3" -> solve += 3
                "four", "4" -> solve += 4
                "five", "5" -> solve += 5
                "six", "6" -> solve += 6
                "seven", "7" -> solve += 7
                "eight", "8" -> solve += 8
                "nine", "9" -> solve += 9
            }
            when(lel.second){
                "one", "1" -> solve += 1
                "two", "2" -> solve += 2
                "three", "3" -> solve += 3
                "four", "4" -> solve += 4
                "five", "5" -> solve += 5
                "six", "6" -> solve += 6
                "seven", "7" -> solve += 7
                "eight", "8" -> solve += 8
                "nine", "9" -> solve += 9
            }
            // println(solve)
            sum += solve.toInt()
            solve = ""
        }
        println(sum)
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_1_test")
    check(part1(testInput) == 142)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
