package com.harsha.organize.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table(name = "comment", schema = "feeds")
data class Comment(
    @Id
    var id: Int?=null,
    @Column(value = "post_id")
    var postId: Int,
    var body: String,
    var email: String?,
    var name: String?,
    var createAt: LocalDateTime,
    var updateAt: LocalDateTime = LocalDateTime.now()
)


