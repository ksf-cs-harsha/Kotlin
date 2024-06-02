package harsha.organize.albums.domain.repo

import harsha.organize.albums.domain.entity.Album
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AlbumRepository : CoroutineCrudRepository<Album, Int>{

}
