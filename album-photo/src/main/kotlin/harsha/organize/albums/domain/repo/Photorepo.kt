package harsha.organize.albums.domain.repo

import harsha.organize.albums.domain.entity.Photo
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PhotosRepository : CoroutineCrudRepository<Photo, Int> {

    @Query("select * from photos where album_id = :albumId")
    suspend fun findAllPhotosByAlbumId(albumId: Int): List<Photo>

}
