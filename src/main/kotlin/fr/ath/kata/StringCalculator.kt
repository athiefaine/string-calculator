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

        private fun getNumbers(expression: String, separators: Collection<String>): List<Int> {
            return getExpressionBody(expression)
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

        private fun getSeparators(expression: String): Collection<String> =
            listOf(DEFAULT_SEPARATOR, *getCustomSeparators(expression).toTypedArray())

        private fun getCustomSeparators(expression: String): Collection<String> {
            return listOf(getExpressionHeader(expression)
                    .removePrefix(CUSTOM_SEPARATOR_HEADER_PREFIX)
                    .removePrefix("[")
                    .removeSuffix("]"))
                    .filter { it.isNotEmpty() }
        }

        private fun getExpressionHeader(expression: String) =
                getExpressionPart(expression, { it -> containsCustomSeparator(it) })

        private fun getExpressionBody(expression: String) =
                getExpressionPart(expression, { it -> containsCustomSeparator(it).not() })

        private fun getExpressionPart(expression: String, predicate: (String) -> Boolean) =
                getLines(expression).filter(predicate).joinToString(DEFAULT_SEPARATOR)

        private fun getLines(expression: String) = expression.split("\n").toMutableList()

        private fun containsCustomSeparator(line: String) = line.startsWith(CUSTOM_SEPARATOR_HEADER_PREFIX)
    }

}
