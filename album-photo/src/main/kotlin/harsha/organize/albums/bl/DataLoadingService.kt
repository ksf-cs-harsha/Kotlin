package harsha.organize.albums.bl

import WebClientConfig
import harsha.organize.albums.domain.dao.AlbumData
import harsha.organize.albums.domain.dao.PhotoData
import harsha.organize.albums.domain.entity.Album
import harsha.organize.albums.domain.entity.Photo
import harsha.organize.albums.domain.repo.AlbumRepository
import harsha.organize.albums.domain.repo.PhotosRepository
import kotlinx.coroutines.reactor.awaitSingle
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.toEntity
import java.time.LocalDateTime

@Service
class DataLoadingService(
    private val albumsRepository: AlbumRepository,
    private val photosRepository: PhotosRepository,
    private val webClientConfig: WebClientConfig
) {

    private val log = LoggerFactory.getLogger(DataLoadingService::class.java)

    private val BASE_URL = "https://jsonplaceholder.typicode.com"

    suspend fun loadAlbums() {
        log.info("In loadAlbums")

        val webClient = webClientConfig.getWebClient()
        val albumDataList = webClient.get()
            .uri("$BASE_URL/albums")
            .retrieve()
            .toEntity<List<AlbumData>>()
            .awaitSingle()
            .body


        val photoDataList = webClient.get()
            .uri("$BASE_URL/photos")
            .retrieve()
            .toEntity<List<PhotoData>>()
            .awaitSingle()
            .body

        if (albumDataList != null && photoDataList != null) {
            log.info("Downloaded albums = ${albumDataList.size}")
            for (albumData in albumDataList) {
                val album = Album(
                    id = albumData.id,
                    title = albumData.title,
                    createAt = LocalDateTime.now()
                )
                albumsRepository.save(album)
            }
            log.info("Downloaded photos = ${photoDataList.size}")
            for (photoData in photoDataList) {
                val photoEntity = Photo(
                    id = photoData.id,
                    title = photoData.title,
                    thumbnailUrl = photoData.thumbnailUrl,
                    url = photoData.url,
                    createAt = LocalDateTime.now(),
                    albumId = photoData.albumId
                )
                photosRepository.save(photoEntity)
            }
            log.info("Return from loadAlbums")
        }
    }
}
