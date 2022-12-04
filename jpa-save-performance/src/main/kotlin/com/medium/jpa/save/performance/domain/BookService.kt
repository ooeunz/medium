package com.medium.jpa.save.performance.domain

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class BookService(
    private val repository: BookRepository,
    private val jdbcTemplate: JdbcTemplate
) {
    fun save(books: List<Book>) {
        books.map { repository.save(it) }
    }

    @Transactional
    fun saveWithTransactional(books: List<Book>) {
        books.map { repository.save(it) }
    }

    fun saveAll(books: List<Book>) {
        repository.saveAll(books)
    }

    fun bulkInsert(books: List<Book>) {
        val values = books.joinToString { "(${it.id}, '${it.contents}')" }
        jdbcTemplate.update(
            """
                INSERT INTO book (id, contents)
                VALUES ${values};
            """.trimIndent()
        )
    }
}
