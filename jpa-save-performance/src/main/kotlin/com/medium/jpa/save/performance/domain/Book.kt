package com.medium.jpa.save.performance.domain

import javax.persistence.*

@Entity
@Table(name = "book")
class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column(name = "title")
    val title: String = "TEST_TITLE",

    @Column(name = "contents")
    val contents: String = "TEST_CONTENTS_DATA"
)
