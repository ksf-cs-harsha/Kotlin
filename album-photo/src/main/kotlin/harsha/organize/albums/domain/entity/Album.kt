package harsha.organize.albums.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table(name = "album")
data class Album(
    @Id
    var id:Int,
    var title: String = "",
    var createAt: LocalDateTime,
    var updateAt: LocalDateTime = LocalDateTime.now(),
)