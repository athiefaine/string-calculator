package fr.ath.kata

class StringCalculator {
    companion object {
        fun add(numbers: String): Int {
            if (numbers.isEmpty()) {
                return 0
            }
            return numbers.toInt()
        }

    }

}
