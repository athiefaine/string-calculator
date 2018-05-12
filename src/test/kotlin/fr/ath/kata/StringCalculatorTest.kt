package fr.ath.kata;

import assertk.assert
import assertk.assertions.isEqualTo
import org.junit.Test

class StringCalculatorTest {

    @Test
    fun should_return_0_when_given_an_empty_string() {
        assert(StringCalculator.add("")).isEqualTo(0)
    }
}