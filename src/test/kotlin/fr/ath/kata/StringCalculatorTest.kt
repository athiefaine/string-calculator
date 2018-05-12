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

    @Test
    fun should_return_sum_when_given_two_comma_separated_numbers() {
        assert(StringCalculator.add("1,2")).isEqualTo(3)
    }

    @Test
    fun should_return_sum_when_given_any_amount_of_comma_separated_numbers() {
        assert(StringCalculator.add("1,2,3,4")).isEqualTo(10)
    }

    @Test
    fun should_return_sum_when_given_any_amount_of_comma_or_newline_separated_numbers() {
        assert(StringCalculator.add("1\n2,3")).isEqualTo(6)
    }
}