import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class SerializeTest {

    private data class Book(
        val isEnglish: String = "Y"
    )

    private val objectMapper = jacksonObjectMapper()

    @Test
    fun `jackson-databind serialize test`() {
        // given
        val book = Book()

        // when
        val actual = objectMapper.writeValueAsString(book)

        // then
        val expected = """{"isEnglish":"Y"}"""
        actual shouldBe expected
    }
}
