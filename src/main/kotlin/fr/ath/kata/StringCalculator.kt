package fr.ath.kata

class StringCalculator {
    companion object {
        private const val CUSTOM_SEPARATOR_HEADER_PREFIX = "//"
        private const val DEFAULT_SEPARATOR = ","
        private const val NUMBER_UPPER_LIMIT = 1000

        fun add(expression: String): Int {
            val separators = getSeparators(expression)

            val numbers = getNumbers(expression, separators)

            handleNegativeNumbers(numbers)

            return compute(numbers)
        }

        private fun compute(numbers: Collection<Int>): Int {
            return numbers.sum()
        }

        private fun getNumbers(numbers: String, separators: MutableList<String>): List<Int> {
            return getExpressionBody(numbers)
                    .split(*separators.toTypedArray())
                    .map { if (it.isEmpty()) 0 else it.toInt() }
                    .filter { it < NUMBER_UPPER_LIMIT }
        }

        private fun handleNegativeNumbers(numbers: Collection<Int>) {
            val negativeNumbers = numbers.filter { it < 0 }
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
            val lines = getLines(numbers)
            if (useCustomSeparator(lines)) {
                return lines[0]
                        .removePrefix(CUSTOM_SEPARATOR_HEADER_PREFIX)
                        .removePrefix("[")
                        .removeSuffix("]")
            }
            return ""
        }

        private fun getExpressionBody(numbers: String): String {
            val lines = getLines(numbers)
            if (useCustomSeparator(lines)) {
                lines.removeAt(0)
            }
            return lines.joinToString(DEFAULT_SEPARATOR)
        }

        private fun getLines(numbers: String) = numbers.split("\n").toMutableList()

        private fun useCustomSeparator(lines: MutableList<String>) =
                lines[0].startsWith(CUSTOM_SEPARATOR_HEADER_PREFIX)

    }

}
