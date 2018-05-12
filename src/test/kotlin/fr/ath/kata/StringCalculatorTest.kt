package fr.ath.kata;

import assertk.assert
import assertk.assertions.isEqualTo
import org.junit.Test

class StringCalculatorTest {

    @Test
    fun should_return_0_when_given_an_empty_string() {
        assert(StringCalculator.add("")).isEqualTo(0)
    }

    @Test
    fun should_return_same_number_when_given_a_single_number() {
        assert(StringCalculator.add("1")).isEqualTo(1)
    }

    @Test
    fun should_return_same_number_when_given_a_single_number_other() {
        assert(StringCalculator.add("42")).isEqualTo(42)
    }
}