package com.java.eval.web.repository;
import com.java.eval.web.model.Album;
import com.java.eval.web.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArtistRepository extends PagingAndSortingRepository<Artist,Integer> {

    Artist findByName(String name);
    @Query(value = "SELECT * FROM artist WHERE artist.Name LIKE CONCAT('%',:name,'%')",nativeQuery = true)
    List<Artist> findByContainName(@Param("name") String name);
    //Page<Artiste> findByNomIgnoreCase(String name, Pageable pageable);


    //List<Album> findByArtiste(Integer artistId);

    //@Query(value ="SELECT * FROM album,artist WHERE artist.ArtistId = :idArtist AND album.ArtistId=artist.ArtistId)", nativeQuery = true)
    //List<Album> findListAlbum(@Param("idArtist") Integer idArtist);
}