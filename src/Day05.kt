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
        var seedInput = input.first().split(' ')
        seedInput = seedInput.subList(1, seedInput.size)
        val seedChunks = seedInput.chunked(2)
        val seedRanges = mutableListOf<MutableList<Long>>()
        seedChunks.forEach {
            seedRanges.add(
                mutableListOf(
                    it.first().toLong(),
                    it.first().toLong() + it.last().toLong() - 1
                )
            )
        }

        // get the indices that separate the transformation maps in the input file
        val indices = mutableListOf<Int>()
        input.forEachIndexed { index: Int, s: String ->
            if (s.contains("map")) {
                indices += index + 1
            }
        }
        // get the transformation list from the input file
        val seedToSoil = input.subList(indices[0], indices[1] - 2)
        val soilToFertilizer = input.subList(indices[1], indices[2] - 2)
        val fertilizerToWater = input.subList(indices[2], indices[3] - 2)
        val waterToLight = input.subList(indices[3], indices[4] - 2)
        val lightToTemperature = input.subList(indices[4], indices[5] - 2)
        val temperatureToHumidity = input.subList(indices[5], indices[6] - 2)
        val humidityToLocation = input.subList(indices[6], input.size)

        val soilsRange = efficientTransformation(seedToSoil, seedRanges)
        val fertilizersRange = efficientTransformation(soilToFertilizer, soilsRange)
        val waterRange = efficientTransformation(fertilizerToWater, fertilizersRange)
        val lightRange = efficientTransformation(waterToLight, waterRange)
        val temperatureRange = efficientTransformation(lightToTemperature, lightRange)
        val humidityRange = efficientTransformation(temperatureToHumidity, temperatureRange)
        val locationRange = efficientTransformation(humidityToLocation, humidityRange)

        var min = 5000000000000
        locationRange.forEach { location: MutableList<Long> ->
            if (location.first() < min) {
                min = location.first()
            }
        }
        return min
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_1_test")
    check(part1(testInput) == 35.toLong())
    check(part2(testInput) == 46.toLong())

    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()

    // check after submitting the solution
    check(part1(input) == 486613012.toLong())
    check(part2(input) == 56931769.toLong())

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

fun efficientTransformation(
    transformationList: List<String>,
    ranges: MutableList<MutableList<Long>>
): MutableList<MutableList<Long>> {
    val transformedRanges = mutableListOf<MutableList<Long>>()
    while (ranges.isNotEmpty()) {
        val range = ranges.first()
        var isTransformationNecessary = false
        for (j in transformationList.indices) {
            val chunks = transformationList[j].split(' ')
            // transformation is necessary if range minimum or range maximum is contained in any transformation
            if (((chunks[1].toLong() <= range.first()) && (range.first() < chunks[1].toLong() + chunks[2].toLong()))
                || ((chunks[1].toLong() <= range.last()) && (range.last() < chunks[1].toLong() + chunks[2].toLong()))
            ) {
                isTransformationNecessary = true
                // if the minimum and maximum are within the same transformation
                if (((chunks[1].toLong() <= range.first()) && (range.first() < chunks[1].toLong() + chunks[2].toLong()))
                    && ((chunks[1].toLong() <= range.last()) && (range.last() < chunks[1].toLong() + chunks[2].toLong()))
                ) {
                    val offset = chunks[0].toLong() - chunks[1].toLong()
                    transformedRanges.add(
                        mutableListOf(
                            range.first() + offset,
                            range.last().toLong() + offset
                        )
                    )
                } // if only the minimum is within the range
                else if ((chunks[1].toLong() <= range.first()) && (range.first() < chunks[1].toLong() + chunks[2].toLong())) {
                    val offset = chunks[0].toLong() - chunks[1].toLong()
                    val maximum = chunks[1].toLong() + chunks[2].toLong()
                    transformedRanges.add(
                        mutableListOf(
                            range.first() + offset,
                            maximum + offset - 1
                        )
                    )
                    ranges.add(mutableListOf(maximum, range.last()))
                } // only the maximum is within the range
                else if ((chunks[1].toLong() <= range.last()) && (range.last() < chunks[1].toLong() + chunks[2].toLong())) {
                    val offset = chunks[0].toLong() - chunks[1].toLong()
                    val minumum = chunks[1].toLong()
                    transformedRanges.add(mutableListOf(minumum + offset, range.last() + offset))
                    ranges.add(mutableListOf(range.first(), minumum - 1))
                }
            }
        }
        if (!isTransformationNecessary) {
            transformedRanges.add(mutableListOf(range.first().toLong(), range.last().toLong()))
        }
        ranges.removeFirst()
    }
    return transformedRanges
}