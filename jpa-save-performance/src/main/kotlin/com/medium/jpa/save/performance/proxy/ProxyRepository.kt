package com.medium.jpa.save.performance.proxy

import com.medium.jpa.save.performance.domain.Book
import com.medium.jpa.save.performance.domain.BookRepository

class ProxyRepository(
    private val repository: BookRepository,
) {
    fun save(book: Book) {
        // Transaction start code
        repository.save(book)
        // Transaction end code
    }
}
