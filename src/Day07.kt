fun main() {

    fun part1(input: List<String>): Int {
        val fives = mutableListOf<String>()
        var fours = mutableListOf<String>()
        val fullhouses = mutableListOf<String>()
        val threes = mutableListOf<String>()
        val twoPairs = mutableListOf<String>()
        val onePairs = mutableListOf<String>()
        val highCards = mutableListOf<String>()
        input.forEach { s: String ->
            val chunks = s.split(' ')
            if (isFive(chunks.first())) {
                fives.add(s)
            } else if (isFour(chunks.first())) {
                fours.add(s)
            } else if(isFullHouse(chunks.first())) {
                fullhouses.add(s)
            } else if(isThree(chunks.first())){
                threes.add(s)
            }else if (isTwoPair(chunks.first())){
                twoPairs.add(s)
            }else if(isOnePair(chunks.first())){
                onePairs.add(s)
            }else{
                highCards.add(s)
            }
        }
        println(fives)
        println(fours)
       /* println(fullhouses)
        println(threes)
        println(twoPairs)
        println(onePairs)
        println(highCards)*/
        //compareBy<Char>{ it.}
        //fours = fours.sortedWith(compareBy<String>{ it.first()}.thenBy{ it[1]}.thenBy{it[2]}).toMutableList()
        // fours.sort(Comparator.comparingChar(p ->))
        //fours = fours.sortedWith(compareBy({ it.customProperty }))
        println(fours)
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_1_test")
    // check(part1(testInput) == 6440)
    // check(part2(testInput) == 71503)

    val input = readInput("Day07")
    part1(input).println()
    // part2(input).println()

    // check after submitting the solution
    // check(part1(input) == 32076)
    // check(part2(input) == 34278221)
}

fun isFive(input: String): Boolean {
    return input[0] == input[1] && input[1] == input[2] && input[2] == input[3] && input[3] == input[4]
}

fun isFour(input: String): Boolean {
    val validLetters = listOf('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2')
    val numbers = mutableListOf<Int>()
    validLetters.forEach{letter: Char ->
        numbers.add(input.count { it == letter})
    }
    return numbers.contains(4)
}

fun isFullHouse(input: String): Boolean {
    val validLetters = listOf('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2')
    val numbers = mutableListOf<Int>()
    validLetters.forEach{letter: Char ->
        numbers.add(input.count { it == letter})
    }
    return numbers.contains(3) && numbers.contains(2)
}

fun isThree(input: String): Boolean {
    val validLetters = listOf('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2')
    val numbers = mutableListOf<Int>()
    validLetters.forEach{letter: Char ->
        numbers.add(input.count { it == letter})
    }
    return numbers.contains(3)
}

fun isTwoPair(input: String): Boolean {
    val validLetters = listOf('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2')
    val numbers = mutableListOf<Int>()
    validLetters.forEach{letter: Char ->
        numbers.add(input.count { it == letter})
    }
    return numbers.filter { it == 2 }.size == 2
}

fun isOnePair(input: String): Boolean {
    val validLetters = listOf('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2')
    val numbers = mutableListOf<Int>()
    validLetters.forEach{letter: Char ->
        numbers.add(input.count { it == letter})
    }
    return numbers.filter { it == 2 }.size == 1
}

/*fun sortList(list: MutableList<String>):MutableList<String>{
    list.forEach { entry: String ->
        var chars = entry.toCharArray()
        println(chars)
    }
    return list
}*/

class CharComparator {
    companion object : Comparator<Char> {
        override fun compare(c1: Char, c2: Char): Int {
            var numberToCharMap = mapOf('A' to 14, 'K' to 13, 'Q' to 12, 'J' to 11, 'T' to 10, '9' to 9, '8' to 8, '7' to 7, '6' to 6, '5' to 5, '4' to 4, '3' to 3, '2' to 2)
            return numberToCharMap[c1]!! - numberToCharMap[c2]!!
        }
    }
}