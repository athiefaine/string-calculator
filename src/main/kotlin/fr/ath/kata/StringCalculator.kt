package fr.ath.kata

class StringCalculator {
    companion object {
        private const val CUSTOM_SEPARATOR_HEADER_PREFIX = "//"
        private const val DEFAULT_SEPARATOR = ","

        fun add(expression: String): Int {
            val separators = getSeparators(expression)

            val numbers = getNumbers(expression, separators)

            handleNegativeNumbers(numbers)

            return compute(numbers, separators)
        }

        private fun compute(numbers: Collection<Int>, separators: MutableList<String>): Int {
            return numbers.sum()
        }

        private fun getNumbers(numbers: String, separators: MutableList<String>): List<Int> {
            return getNumbersBody(numbers)
                    .split(*separators.toTypedArray())
                    .map { if (it.isEmpty()) 0 else it.toInt() }
        }

        private fun handleNegativeNumbers(numbers: Collection<Int>): Unit {
            val negativeNumbers = numbers.filter { n -> n < 0 }
            if (negativeNumbers.isNotEmpty()) {
                throw RuntimeException("negatives not allowed: $negativeNumbers")
            }
        }

        private fun getSeparators(numbers: String): MutableList<String> {
            val separators = mutableListOf(DEFAULT_SEPARATOR)
            val customSeparator = getCustomSeparator(numbers)
            if (customSeparator.isNotEmpty()) {
                separators.add(customSeparator)
            }
            return separators
        }

        private fun getCustomSeparator(numbers: String): String {
            val lines = numbers.split("\n").toMutableList()
            if (lines[0].startsWith(CUSTOM_SEPARATOR_HEADER_PREFIX)) {
                return lines[0].removePrefix(CUSTOM_SEPARATOR_HEADER_PREFIX)
            }
            return ""
        }

        private fun getNumbersBody(numbers: String): String {
            val lines = numbers.split("\n").toMutableList()
            if (lines[0].startsWith(CUSTOM_SEPARATOR_HEADER_PREFIX)) {
                lines.removeAt(0)
            }
            return lines.joinToString(DEFAULT_SEPARATOR)
        }

    }

}
