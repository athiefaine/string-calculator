package fr.ath.kata

class StringCalculator {
    companion object {
        private const val CUSTOM_SEPARATOR_HEADER_PREFIX = "//"
        private const val DEFAULT_SEPARATOR = ","
        private const val NUMBER_UPPER_LIMIT = 1000

        fun add(expression: String): Int {
            val separators = getSeparators(expression)
            val numbers = getNumbers(expression, separators)
            checkForNegativeNumbers(numbers)
            return compute(numbers)
        }

        private fun compute(numbers: Collection<Int>): Int = numbers.sum()

        private fun getNumbers(expression: String, separators: Collection<String>): List<Int> =
                getExpressionBody(expression)
                        .split(*separators.toTypedArray())
                        .filterNot(String::isEmpty)
                        .map(String::toInt)
                        .filter { it < NUMBER_UPPER_LIMIT }

        private fun checkForNegativeNumbers(numbers: Collection<Int>) {
            val negativeNumbers = numbers.filter { it < 0 }
            if (negativeNumbers.any()) {
                throw RuntimeException("negatives not allowed: $negativeNumbers")
            }
        }

        private fun getSeparators(expression: String): Collection<String> =
                listOf(DEFAULT_SEPARATOR, *getCustomSeparators(expression).toTypedArray())

        private fun getCustomSeparators(expression: String): Collection<String> =
                getExpressionHeader(expression).removePrefix(CUSTOM_SEPARATOR_HEADER_PREFIX)
                        .split("[")
                        .map { it.removeSuffix("]") }
                        .filterNot(String::isEmpty)

        private fun getExpressionHeader(expression: String) =
                getExpressionPart(expression, { it -> containsCustomSeparator(it) })

        private fun getExpressionBody(expression: String) =
                getExpressionPart(expression, { it -> containsCustomSeparator(it).not() })

        private fun getExpressionPart(expression: String, predicate: (String) -> Boolean) =
                expression.split("\n").filter(predicate).joinToString(DEFAULT_SEPARATOR)

        private fun containsCustomSeparator(line: String) = line.startsWith(CUSTOM_SEPARATOR_HEADER_PREFIX)
    }
}
