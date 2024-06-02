package harsha.organize.albums.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table(name = "photos")
data class Photo(
    @Id
    var id: Int,
    @Column(value = "album_id")
    var albumId: Int,
    var title: String = "",
    var url: String = "",
    var thumbnailUrl: String = "",
    var createAt: LocalDateTime,
    var updateAt: LocalDateTime = LocalDateTime.now(),
)
