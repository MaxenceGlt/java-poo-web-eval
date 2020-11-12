package com.java.eval.web.repository;
import com.java.eval.web.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtisteRepository extends JpaRepository<Artist, Integer> {

    Artist findByName(String name);

    //Page<Artiste> findByNomIgnoreCase(String name, Pageable pageable);

    //List<Album> findByArtiste(Integer artistId);

    //@Query(value ="SELECT * FROM album,artist WHERE artist.ArtistId = :idArtist AND album.ArtistId=artist.ArtistId)", nativeQuery = true)
    //List<Album> findListAlbum(@Param("idArtist") Integer idArtist);
}