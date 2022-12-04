package com.medium.jpa.save.performance

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JpaSavePerformanceApplication

fun main(args: Array<String>) {
    runApplication<JpaSavePerformanceApplication>(*args)
}
