package harsha.organize.albums.bl

import harsha.organize.albums.domain.dao.AlbumOutput
import harsha.organize.albums.domain.dao.PhotoOutput
import harsha.organize.albums.domain.repo.AlbumRepository
import harsha.organize.albums.domain.repo.PhotosRepository
import org.springframework.stereotype.Service

@Service
class AlbumService(
    private val photosRepository: PhotosRepository,
    private val albumRepository: AlbumRepository
) {

    suspend fun getAlbumById(albumId: Int): AlbumOutput {
        val photoList = photosRepository.findAllPhotosByAlbumId(albumId)
        val photoOutputList = mutableListOf<PhotoOutput>()
        for (photo in photoList) {
            val photoOutput = PhotoOutput(photo.id, photo.title, photo.url, photo.thumbnailUrl)
            photoOutputList.add(photoOutput)
        }

        val albumEntity = albumRepository.findById(albumId)!!

        return AlbumOutput(albumEntity.id, albumEntity.title, photoOutputList)
    }
}
