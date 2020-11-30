package com.java.eval.web.controller;

import com.java.eval.web.model.Artist;
import com.java.eval.web.repository.AlbumRepository;
import com.java.eval.web.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/artists")

public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private AlbumRepository albumRepository;


  /*  @RequestMapping(
            method = RequestMethod.GET, //Méthode HTTP : GET/POST/PATCH/PUT/DELETE
            value = "/count" //Chemin du mapping (concaténé avec l'éventuel chemin présent au niveau du contrôleur)
    )
    public Long countArtist(){return artistRepository.count(); }
*/
    //RECHERCHE D'UN ARTIST D'ID
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/{id}",
            produces =MediaType.APPLICATION_JSON_VALUE
    )
    public Artist findArtisteById(@PathVariable int id){
        Optional<Artist> artiste = artistRepository.findById(id);
        if(artiste.isEmpty()){
            throw new EntityNotFoundException("L'employe d'id  " + id + " n'a pas été trouvé !");
        }
        return artiste.get();
    }

//RECHERCHE PAR NOM
    @RequestMapping(
            value="",
            method = RequestMethod.GET,
            produces =MediaType.APPLICATION_JSON_VALUE,
            params = "name")
    public List<Artist> findArtisteByName(@RequestParam("name") String name){
        List<Artist> artist = artistRepository.findByContainName(name);
        return artist;
    }

    //AFFICHAGE PAJE WEB AVEC LES PAJES -- TRIER BY ASC
    @RequestMapping(
            value="",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Page<Artist> findAll(@RequestParam(value = "page",defaultValue = "0") Integer page ,
                                @RequestParam(value="size", defaultValue = "10")Integer size,
                                @RequestParam(value = "sortProperty", defaultValue = "name") String sortProperty,
                                @RequestParam(value = "sortDirection", defaultValue = "ASC") String sortDirection)
    {
        Pageable pageable = PageRequest.of(page,size, Sort.Direction.fromString(sortDirection),sortProperty);
        return artistRepository.findAll(pageable);
    }

    //CREATION D'UN EMPLOYE + ERREUR 409
    @RequestMapping(
            value="",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces =MediaType.APPLICATION_JSON_VALUE)
    public Artist createArtist(
            @RequestBody Artist artist){
        if(artistRepository.findByName(artist.getName())!=null){
           throw new EntityExistsException("Il existe deja !");
        }else {
            return artistRepository.save(artist);
        }
    }

    //MODIFICATION ARTIST
    @RequestMapping(
            value="{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces =MediaType.APPLICATION_JSON_VALUE
    )
    public Artist updateArtist(
            @RequestBody Artist artist,@PathVariable Integer id){
        return artistRepository.save(artist);
    }

    //DELETE ARTIST
    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteArtist (@PathVariable("id") Integer id) {
        artistRepository.deleteById(id);
        albumRepository.deleteByArtistId(id);
    }


}
