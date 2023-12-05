fun main() {

    fun part1(input: List<String>): Long {
        var seeds = input[0].split(' ')
        seeds = seeds.subList(1, seeds.size)
        var seedsInt = mutableListOf<Long>()
        seeds.forEach { seed: String ->
            seedsInt.add(seed.toLong())
        }
        // println(seeds)
        // var maps = input.subList(2, input.size).filter { it.isNotBlank() }
        var indexes = mutableListOf<Int>()
        input.forEachIndexed { index: Int, s: String ->
            if (s.contains("map")) {
                indexes += index + 1
            }
        }
        var seedToSoil = input.subList(indexes[0], indexes[1] - 2)
        var soilToFertilizer = input.subList(indexes[1], indexes[2] - 2)
        var fertilizerToWater = input.subList(indexes[2], indexes[3] - 2)
        var waterToLight = input.subList(indexes[3], indexes[4] - 2)
        var lightToTemperature = input.subList(indexes[4], indexes[5] - 2)
        var temperatureToHumidity = input.subList(indexes[5], indexes[6] - 2)
        var humidityToLocation = input.subList(indexes[6], input.size)

        var soils = mutableListOf<Long>()
        seedsInt.forEach { seed: Long ->
            soils.add(doImportantStuff(seedToSoil, seed)!!)
        }
        // println(soils)
        var fertilizers = mutableListOf<Long>()
        soils.forEach { soil: Long ->
            fertilizers.add(doImportantStuff(soilToFertilizer, soil)!!)
        }
        //println(fertilizers)
        var waters = mutableListOf<Long>()
        fertilizers.forEach { fertilizer: Long ->
            waters.add(doImportantStuff(fertilizerToWater, fertilizer)!!)
        }
        //println(waters)
        var lights = mutableListOf<Long>()
        waters.forEach { water: Long ->
            lights.add(doImportantStuff(waterToLight, water)!!)
        }
        //println(lights)
        var temperatures = mutableListOf<Long>()
        lights.forEach { light: Long ->
            temperatures.add(doImportantStuff(lightToTemperature, light)!!)
        }
        //println(temperatures)
        var humidities = mutableListOf<Long>()
        temperatures.forEach { temperature: Long ->
            humidities.add(doImportantStuff(temperatureToHumidity, temperature)!!)
        }
        //println(humidities)
        var locations = mutableListOf<Long>()
        humidities.forEach { humidity: Long ->
            locations.add(doImportantStuff(humidityToLocation, humidity)!!)
        }
        //println(locations)
        return locations.min()
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_1_test")
    check(part1(testInput) == 35.toLong())
    // check(part2(testInput) == 30)

    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()

    // check after submitting the solution
    // check(part1(input) == 23441)
    // check(part2(input) == 5923918)
}

fun doImportantStuff(transformationList: List<String>, seed: Long): Long? {
    var transformationMap = mutableMapOf<Long, Long>()
    var destinations = mutableListOf<Long>()
    var sources = mutableListOf<Long>()
    var lengths = mutableListOf<Long>()
    transformationList.forEach { s: String ->
        var numbers = s.split(' ')
        var destination = numbers[0].toLong()
        destinations.add(destination)
        var source = numbers[1].toLong()
        sources.add(source)
        var length = numbers[2].toLong()
        lengths.add(length)
    }
    destinations.forEachIndexed { index: Int, _ ->
        for (i in 0..<lengths[index]) {
            transformationMap += mapOf(sources[index] + i to (destinations[index] + i))
        }
    }
    // println(transformationMap)
    return if (transformationMap[seed] != null) {
        transformationMap[seed]
    } else {
        seed
    }
}