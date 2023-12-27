fun main() {

    /***
     * part1:
     * find the lowest location number of any seed
     */
    fun part1(input: List<String>): Long {
        var seeds = input[0].split(' ')
        seeds = seeds.subList(1, seeds.size)
        val seedsLong = mutableListOf<Long>()
        seeds.forEach { seed: String ->
            seedsLong.add(seed.toLong())
        }

        val mapSeparators = mutableListOf<Int>()
        input.forEachIndexed { index: Int, s: String ->
            if (s.contains("map")) {
                mapSeparators.add(index + 1)
            }
        }
        val seedToSoil = input.subList(mapSeparators.first(), mapSeparators[1] - 2)
        val soilToFertilizer = input.subList(mapSeparators[1], mapSeparators[2] - 2)
        val fertilizerToWater = input.subList(mapSeparators[2], mapSeparators[3] - 2)
        val waterToLight = input.subList(mapSeparators[3], mapSeparators[4] - 2)
        val lightToTemperature = input.subList(mapSeparators[4], mapSeparators[5] - 2)
        val temperatureToHumidity = input.subList(mapSeparators[5], mapSeparators.last() - 2)
        val humidityToLocation = input.subList(mapSeparators.last(), input.size)

        val soils = mutableListOf<Long>()
        seedsLong.forEach { seed: Long ->
            soils.add(seedTransformation(seedToSoil, seed))
        }
        val fertilizers = mutableListOf<Long>()
        soils.forEach { soil: Long ->
            fertilizers.add(seedTransformation(soilToFertilizer, soil))
        }
        val waters = mutableListOf<Long>()
        fertilizers.forEach { fertilizer: Long ->
            waters.add(seedTransformation(fertilizerToWater, fertilizer))
        }
        val lights = mutableListOf<Long>()
        waters.forEach { water: Long ->
            lights.add(seedTransformation(waterToLight, water))
        }
        val temperatures = mutableListOf<Long>()
        lights.forEach { light: Long ->
            temperatures.add(seedTransformation(lightToTemperature, light))
        }
        val humidities = mutableListOf<Long>()
        temperatures.forEach { temperature: Long ->
            humidities.add(seedTransformation(temperatureToHumidity, temperature))
        }
        val locations = mutableListOf<Long>()
        humidities.forEach { humidity: Long ->
            locations.add(seedTransformation(humidityToLocation, humidity))
        }
        return locations.min()
    }

    /***
     * part2:
     * interpret the seed as ranges
     */
    fun part2(input: List<String>): Long {
        var seedInput = input.first().split(' ')
        seedInput = seedInput.subList(1, seedInput.size)
        val seedChunks = seedInput.chunked(2)
        var seedRanges = mutableListOf<MutableList<Long>>()
        seedChunks.forEach {
            seedRanges.add(
                mutableListOf(
                    it.first().toLong(),
                    it.first().toLong() + it.last().toLong() - 1
                )
            )
        }

        // get the indices that separate the transformation maps in the input file
        val mapSeparators = mutableListOf<Int>()
        input.forEachIndexed { index: Int, s: String ->
            if (s.contains("map")) {
                mapSeparators += index + 1
            }
        }
        mapSeparators.add(input.size + 2)

        var mutatedRanges = mutableListOf<MutableList<Long>>()
        for (i in 0..mapSeparators.size - 2) {
            val transformationList = input.subList(mapSeparators[i], mapSeparators[i + 1] - 2)
            mutatedRanges = efficientTransformation(transformationList, seedRanges)
            seedRanges = mutatedRanges
        }

        var min = 5000000000000
        mutatedRanges.forEach { location: MutableList<Long> ->
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

fun seedTransformation(transformationList: List<String>, seed: Long): Long {
    var mutableSeed = seed
    for (i in transformationList.indices) {
        val chunks = transformationList[i].split(' ')
        val destination = chunks.first().toLong()
        val source = chunks[1].toLong()
        val length = chunks.last().toLong()
        if ((source <= mutableSeed) && (source + length >= mutableSeed)) {
            mutableSeed += destination - source
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
        transformationList.forEach { transformation: String ->
            val chunks = transformation.split(' ')
            val destination = chunks.first().toLong()
            val source = chunks[1].toLong()
            val length = chunks.last().toLong()
            // transformation is necessary if range minimum or range maximum is contained in any transformation
            if (((source <= range.first()) && (range.first() < source + length))
                || ((source <= range.last()) && (range.last() < source + length))
            ) {
                isTransformationNecessary = true
                // if the minimum and maximum are within the same transformation
                if (((source <= range.first()) && (range.first() < source + length))
                    && ((source <= range.last()) && (range.last() < source + length))
                ) {
                    val offset = destination - source
                    transformedRanges.add(
                        mutableListOf(
                            range.first() + offset,
                            range.last().toLong() + offset
                        )
                    )
                } // if only the minimum is within the range
                else if ((source <= range.first()) && (range.first() < source + length)) {
                    val offset = destination - source
                    val maximum = source + length
                    transformedRanges.add(
                        mutableListOf(
                            range.first() + offset,
                            maximum + offset - 1
                        )
                    )
                    ranges.add(mutableListOf(maximum, range.last()))
                } // only the maximum is within the range
                else if ((source <= range.last()) && (range.last() < source + length)) {
                    val offset = destination - source
                    transformedRanges.add(mutableListOf(source + offset, range.last() + offset))
                    ranges.add(mutableListOf(range.first(), source - 1))
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