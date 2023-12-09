fun main() {

    fun part1(input: List<String>): Long {
        var seeds = input[0].split(' ')
        seeds = seeds.subList(1, seeds.size)
        val seedsInt = mutableListOf<Long>()
        seeds.forEach { seed: String ->
            seedsInt.add(seed.toLong())
        }
        // var maps = input.subList(2, input.size).filter { it.isNotBlank() }
        var indexes = mutableListOf<Int>()
        input.forEachIndexed { index: Int, s: String ->
            if (s.contains("map")) {
                indexes += index + 1
            }
        }
        val seedToSoil = input.subList(indexes[0], indexes[1] - 2)
        val soilToFertilizer = input.subList(indexes[1], indexes[2] - 2)
        val fertilizerToWater = input.subList(indexes[2], indexes[3] - 2)
        val waterToLight = input.subList(indexes[3], indexes[4] - 2)
        val lightToTemperature = input.subList(indexes[4], indexes[5] - 2)
        val temperatureToHumidity = input.subList(indexes[5], indexes[6] - 2)
        val humidityToLocation = input.subList(indexes[6], input.size)

        val soils = mutableListOf<Long>()
        seedsInt.forEach { seed: Long ->
            soils.add(doImportantStuff(seedToSoil, seed))
        }
        val fertilizers = mutableListOf<Long>()
        soils.forEach { soil: Long ->
            fertilizers.add(doImportantStuff(soilToFertilizer, soil))
        }
        val waters = mutableListOf<Long>()
        fertilizers.forEach { fertilizer: Long ->
            waters.add(doImportantStuff(fertilizerToWater, fertilizer))
        }
        val lights = mutableListOf<Long>()
        waters.forEach { water: Long ->
            lights.add(doImportantStuff(waterToLight, water))
        }
        val temperatures = mutableListOf<Long>()
        lights.forEach { light: Long ->
            temperatures.add(doImportantStuff(lightToTemperature, light))
        }
        val humidities = mutableListOf<Long>()
        temperatures.forEach { temperature: Long ->
            humidities.add(doImportantStuff(temperatureToHumidity, temperature))
        }
        val locations = mutableListOf<Long>()
        humidities.forEach { humidity: Long ->
            locations.add(doImportantStuff(humidityToLocation, humidity))
        }
        return locations.min()
    }

    fun part2(input: List<String>): Long {
        var location = 5000000000
        var seeds = input[0].split(' ')
        seeds = seeds.subList(1, seeds.size)
        val seedsInt = mutableListOf<Long>()
        seeds.forEach { seed: String ->
            seedsInt.add(seed.toLong())
        }
        val seedChunks = seedsInt.chunked(2)
        val indexes = mutableListOf<Int>()
        input.forEachIndexed { index: Int, s: String ->
            if (s.contains("map")) {
                indexes += index + 1
            }
        }
        val seedToSoil = input.subList(indexes[0], indexes[1] - 2)
        val soilToFertilizer = input.subList(indexes[1], indexes[2] - 2)
        val fertilizerToWater = input.subList(indexes[2], indexes[3] - 2)
        val waterToLight = input.subList(indexes[3], indexes[4] - 2)
        val lightToTemperature = input.subList(indexes[4], indexes[5] - 2)
        val temperatureToHumidity = input.subList(indexes[5], indexes[6] - 2)
        val humidityToLocation = input.subList(indexes[6], input.size)

        var soil: Long
        for (chunk in seedChunks) {
            for (seed in chunk.first()..<chunk.first() + chunk.last()) {
                soil = doImportantStuff(seedToSoil, seed)
                val fertilizer = doImportantStuff(soilToFertilizer, soil)
                val water = doImportantStuff(fertilizerToWater, fertilizer)
                val light = doImportantStuff(waterToLight, water)
                val temperature = doImportantStuff(lightToTemperature, light)
                val humidity = doImportantStuff(temperatureToHumidity, temperature)
                val result = doImportantStuff(humidityToLocation, humidity)
                if (result < location) {
                    location = result
                }
                println(location)
            }
            //println(location)
        }
        return location
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_1_test")
    // check(part1(testInput) == 35.toLong())
    check(part2(testInput) == 46.toLong())

    val input = readInput("Day05")
    // part1(input).println()
    part2(input).println()

    // check after submitting the solution
    // check(part1(input) == 486613012.toLong())
    // check(part2(input) == 5923918)
}

fun doImportantStuff(transformationList: List<String>, seed: Long): Long {
    var mutableSeed = seed
    for (i in transformationList.indices) {
        val chunks = transformationList[i].split(' ')
        if ((chunks[1].toLong() <= mutableSeed) && (chunks[1].toLong() + chunks[2].toLong() >= mutableSeed)) {
            mutableSeed += chunks[0].toLong() - chunks[1].toLong()
            break
        }
    }
    return mutableSeed
}