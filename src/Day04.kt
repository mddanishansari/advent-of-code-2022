fun main() {

    fun IntRange.contains(other: IntRange): Boolean {
        return contains(other.max()) && contains(other.min())
    }

    fun IntRange.doesOverlap(other: IntRange): Boolean {
        return contains(other.max()) || contains(other.min())
    }

    fun List<String>.pairOfRange(): List<Pair<IntRange, IntRange>> {
        return map { it.split(",") }
            .map {
                val firstRangeMin = it.first().split("-").first().toInt()
                val firstRangeMax = it.first().split("-").last().toInt()
                val secondRangeMin = it.last().split("-").first().toInt()
                val secondRangeMax = it.last().split("-").last().toInt()
                Pair(firstRangeMin..firstRangeMax, secondRangeMin..secondRangeMax)
            }
    }

    fun part1(input: List<String>): Int {
        var fullyContainedPairs = 0
        input.pairOfRange().forEach {
            if (it.first.contains(it.second) || it.second.contains(it.first)) {
                fullyContainedPairs++
            }
        }
        return fullyContainedPairs
    }

    fun part2(input: List<String>): Int {
        var overlappingPairs = 0
        input.pairOfRange().forEach {
            if (it.first.doesOverlap(it.second)|| it.second.doesOverlap(it.first)) {
                overlappingPairs++
            }
        }
        return overlappingPairs
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
