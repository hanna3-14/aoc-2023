fun main() {

    fun part1(input: List<String>): Int {
        var totalsum = 0
        val strings = input.first().split(',')
        strings.forEach { line: String ->
            var hash = 0
            line.forEach { letter: Char ->
                hash += letter.code
                hash *= 17
                hash %= 256
            }
            totalsum += hash
        }
        return totalsum
    }

    fun part2(input: List<String>): Int {
        val boxes = MutableList(256) { _ -> (mutableListOf<String>()) }
        val strings = input.first().split(',')

        // sort strings based on their hashes
        strings.forEach { line: String ->
            var hash = 0
            line.forEach { letter: Char ->
                if (!letter.isDigit() && letter != '-' && letter != '=') {
                    hash += letter.code
                    hash *= 17
                    hash %= 256
                }
            }
            boxes[hash].add(line)
        }

        // sort within the boxes
        val sortedBoxes = mutableListOf<MutableMap<String, Int>>()
        boxes.forEach { box: MutableList<String> ->
            val map = mutableMapOf<String, Int>()
            box.forEach { element: String ->
                if (element.contains('-')) {
                    var label = element.dropLast(1)
                    map.remove(label)
                }
                if (element.contains('=')) {
                    val label = element.dropLast(2)
                    map += mapOf(label to element.last().digitToInt())
                }
            }
            sortedBoxes.add(map)
        }

        // calculate the value
        var totoalsum = 0
        sortedBoxes.forEachIndexed { boxIndex: Int, box: MutableMap<String, Int> ->
            var sum = 0
            val numbers = box.values
            numbers.forEachIndexed { numberIndex: Int, focalLength: Int ->
                sum += (boxIndex + 1) * (numberIndex + 1) * focalLength
            }
            totoalsum += sum
        }
        return totoalsum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day15_1_test")
    check(part1(testInput) == 1320)
    check(part2(testInput) == 145)

    val input = readInput("Day15")
    part1(input).println()
    part2(input).println()

    // check after submitting the solution
    check(part1(input) == 511257)
    check(part2(input) == 239484)
}
