fun main() {
    fun <T> List<T>.toPair(): Pair<T, T> {
        if (this.size != 2) {
            throw IllegalArgumentException("List is not of length 2!")
        }
        return Pair(this[0], this[1])
    }

    fun round(round: String): Pair<String, String> {
        return round.replace("X", "A")
            .replace("Y", "B")
            .replace("Z", "C")
            .split(" ")
            .toPair()
    }

    fun part1(input: List<String>): Int {
        // A, X = Rock
        // B, Y = Paper
        // C, Z = Scissor
        fun decideWinnerAndGetScore(opponentSelection: String, playerSelection: String): Int {
            // Draw
            if (opponentSelection == playerSelection) return 3

            // Won
            if ((playerSelection == "A" && opponentSelection == "C") ||
                (playerSelection == "B" && opponentSelection == "A") ||
                (playerSelection == "C" && opponentSelection == "B")
            )
                return 6

            // Lost
            return 0
        }

        var score = 0
        input.forEach {
            val (opponentSelection, playerSelection) = round(it)
            score += when (playerSelection) {
                "A" -> 1
                "B" -> 2
                "C" -> 3
                else -> 0
            }
            score += decideWinnerAndGetScore(opponentSelection, playerSelection)
        }
        return score
    }

    fun part2(input: List<String>): Int {
        fun calculateScore(opponentSelection: String, whatToDo: String): Int {
            // Player has to Draw
            if (whatToDo == "B") {
                if (opponentSelection == "A") return 1
                if (opponentSelection == "B") return 2
                if (opponentSelection == "C") return 3
            }

            // Player has to Lose
            if (whatToDo == "A") {
                if (opponentSelection == "A") return 3
                if (opponentSelection == "B") return 1
                if (opponentSelection == "C") return 2
            }

            // Player has to Win
            if (opponentSelection == "A") return 2
            if (opponentSelection == "B") return 3
            if (opponentSelection == "C") return 1

            // Dafuq?
            return 0
        }

        var score = 0
        input.forEach {
            val (opponentSelection, whatToDo) = round(it)
            score += when (whatToDo) {
                "A" -> 0
                "B" -> 3
                "C" -> 6
                else -> 0
            }
            score += calculateScore(opponentSelection, whatToDo)
        }

        return score
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
