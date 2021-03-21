package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class LottoTest {

    @ParameterizedTest
    @MethodSource("provideInvalidNumbers")
    internal fun `로또 1장은 1~45까지의 중복되지 않는 숫자 6개로 이뤄져있다`(invalidNumbers: List<Int>) {
        assertDoesNotThrow { Lotto(listOf(1, 2, 3, 4, 5, 6)) }
        assertThrows<IllegalArgumentException> { Lotto(invalidNumbers) }
    }

    @Test
    internal fun `로또를 자동으로 구입할 수 있다`() {
        val lotto = Lotto.buyRandom()
        assertThat(lotto.numbers.size).isEqualTo(6)
    }

    companion object {
        @JvmStatic
        private fun provideInvalidNumbers(): Stream<Arguments?>? {
            return Stream.of(
                Arguments.of(listOf(1, 2, 3, 4, 5, 46)),
                Arguments.of(listOf(1, 1, 3, 4, 5, 6)),
                Arguments.of(listOf(1, 2, 3, 4, 5, 6, 7)),
                Arguments.of(listOf(1))
            )
        }
    }
}