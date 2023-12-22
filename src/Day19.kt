fun main() {

    fun part1(input: List<String>): Int {
        var xmasRating = 0

        // split the input into workflows and ratings
        var emptyLineIndex = 0
        input.forEachIndexed { index, s ->
            if (s.isBlank()) {
                emptyLineIndex = index
            }
        }
        val workflows = input.subList(0, emptyLineIndex)
        val ratings = input.subList(emptyLineIndex + 1, input.size)

        for (i in ratings.indices) {
            val ratingMap = mutableMapOf<String, Int>() // contains a single rating as mapping
            ratings[i].filter { it != '{' }.filter { it != '}' }.split(',').forEach { mapping: String ->
                ratingMap += mapOf(mapping.split('=').first() to mapping.split('=').last().toInt())
            }
            var instruction = "in"
            while (instruction != "A" && instruction != "R") {
                instruction = doInstruction(workflows, instruction, ratingMap)
            }
            if (instruction == "A") {
                xmasRating += ratingMap["x"]!! + ratingMap["m"]!! + ratingMap["a"]!! + ratingMap["s"]!!
            }
        }

        return xmasRating
    }

    fun part2(input: List<String>): Int {
        return input.size
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day19_1_test")
    check(part1(testInput) == 19114)

    val input = readInput("Day19")
    part1(input).println()
    // part2(input).println()

    // check after submitting the solution
    check(part1(input) == 480738)
    // check(part2(input) == 495)
}

fun doInstruction(workflows: List<String>, instruction: String, ratingMap: Map<String, Int>): String {
    var nextInstruction = ""
    workflows.forEach { workflow: String ->
        var instructionsList = mutableListOf<String>()
        if (workflow.contains(instruction)) {
            if (workflow.take(instruction.length) == instruction && workflow[instruction.length] == '{') {
                instructionsList =
                    workflow.drop(instruction.length).filter { it != '{' }.filter { it != '}' }.split(',')
                        .toMutableList()
            }
        }
        if (instructionsList.isNotEmpty()) {
            for (i in instructionsList.indices) {
                nextInstruction = instructionsList[i]
                if (nextInstruction == "A" || nextInstruction == "R") {
                    return nextInstruction
                } else {
                    val comparison = nextInstruction.split(":").first()
                    var letter = ""
                    var comparisonValue = 0
                    if (comparison.contains('<')) {
                        letter = comparison.split('<').first()
                        comparisonValue = comparison.split('<').last().toInt()
                        if (ratingMap[letter.first().toString()]!! < comparisonValue) {
                            nextInstruction = nextInstruction.split(":").last()
                            break
                        } else {
                            continue
                        }
                    } else if (comparison.contains('>')) {
                        letter = comparison.split('>').first()
                        comparisonValue = comparison.split('>').last().toInt()
                        if (ratingMap[letter]!! > comparisonValue) {
                            nextInstruction = nextInstruction.split(":").last()
                            break
                        } else {
                            continue
                        }
                    }
                }
            }
        }
    }
    return nextInstruction
}