package harsha.organize.albums

import WebClientConfig
import harsha.organize.albums.bl.DataLoadingService
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@SpringBootApplication
@EnableR2dbcRepositories
@EnableAutoConfiguration
@Import(WebClientConfig::class)
class AlbumPhotosApplication(private val dataLoadingService: DataLoadingService) : CommandLineRunner {

    private val log = LoggerFactory.getLogger(AlbumPhotosApplication::class.java)

    override fun run(vararg args: String?) {
        log.info("In run")
        runBlocking {
            dataLoadingService.loadAlbums()
        }
        log.info("Return from run")
    }
}

fun main(args: Array<String>) {
    runApplication<AlbumPhotosApplication>(*args)
}
