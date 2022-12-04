package com.medium.jpa.save.performance.domain

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.util.StopWatch

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
internal class BookBulkInsertTest {

    @Autowired
    lateinit var repository: BookRepository

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    private fun generateBooks(): List<Book> {
        return (1..1000).map { Book() }
    }

    @Test
    fun `save`() {
        val books = generateBooks()
        val stopWatch = StopWatch()

        stopWatch.start()
        books.map { repository.save(it) }
        stopWatch.stop()

        println("Execute time ${stopWatch.totalTimeMillis}ms")
    }

    @Test
    fun `saveAll`() {
        val books = generateBooks()
        val stopWatch = StopWatch()

        stopWatch.start()
        repository.saveAll(books)
        stopWatch.stop()

        println("Execute time ${stopWatch.totalTimeMillis}ms")
    }

    @Test
    fun `bulk insert`() {
        val books = generateBooks()
        val stopWatch = StopWatch()

        stopWatch.start()
        val values = books.joinToString { "('${it.title}', '${it.contents}')" }
        jdbcTemplate.update(
            """
                INSERT INTO book (title, contents)
                VALUES ${values};
            """.trimIndent()
        )
        stopWatch.stop()

        println("Execute time ${stopWatch.totalTimeMillis}ms")
    }
}
