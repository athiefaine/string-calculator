package fr.ath.kata

class StringCalculator {
    companion object {
        fun add(numbers: String): Int {
            return numbers
                    .split(",", "\n")
                    .map { if (it.isEmpty()) 0 else it.toInt() }
                    .sum()
        }

    }

}
