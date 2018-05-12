package fr.ath.kata

class StringCalculator {
    companion object {
        private const val CUSTOM_SEPARATOR_HEADER_PREFIX = "//"
        private const val DEFAULT_SEPARATOR = ","

        fun add(numbers: String): Int {
            val separators = getSeparators(numbers)

            return compute(numbers, separators)
        }

        private fun compute(numbers: String, separators: MutableList<String>): Int {
            return getNumbersBody(numbers)
                    .split(*separators.toTypedArray())
                    .map { if (it.isEmpty()) 0 else it.toInt() }
                    .sum()
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
