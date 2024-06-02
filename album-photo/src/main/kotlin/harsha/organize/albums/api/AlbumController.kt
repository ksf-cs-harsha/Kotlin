package harsha.organize.albums.api

import harsha.organize.albums.domain.dao.AlbumOutput
import harsha.organize.albums.bl.AlbumService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class AlbumController(private val albumService: AlbumService) {

    @GetMapping("/album/{albumId}")
    suspend fun getAlbumById(@PathVariable("albumId") id: Int): AlbumOutput {
        return albumService.getAlbumById(id)
    }
}
