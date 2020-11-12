package com.java.eval.web.controller;

import com.java.eval.web.model.Artist;
import com.java.eval.web.repository.AlbumRepository;
import com.java.eval.web.repository.ArtisteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/artists")

public class ArtistController {

    @Autowired
    private ArtisteRepository artisteRepository;
    @Autowired
    private AlbumRepository albumRepository;


    @RequestMapping(
            method = RequestMethod.GET, //Méthode HTTP : GET/POST/PATCH/PUT/DELETE
            value = "/count" //Chemin du mapping (concaténé avec l'éventuel chemin présent au niveau du contrôleur)
    )
    public Long countArtist(){return artisteRepository.count(); }

    //RECHERCHE D'UN ARTIST D'ID 1
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/1",
            produces =MediaType.APPLICATION_JSON_VALUE
    )
    public Artist findArtisteById(){
        Optional<Artist> artiste = artisteRepository.findById(1);
        return artiste.get();
    }

//RECHERCHE PAR NOM
    @RequestMapping(
            value="",
            method = RequestMethod.GET,
            produces =MediaType.APPLICATION_JSON_VALUE,
            params = "name"
    )
    public Artist findArtisteByName(@RequestParam("name") String name){
        Artist artist = artisteRepository.findByName(name);
        return artist;
    }

    //AFFICHAGE PAJE WEB AVEC LES PAJES -- TRIER BY ASC
    @RequestMapping(
            value="",
            method = RequestMethod.GET, //Méthode HTTP : GET/POST/PATCH/PUT/DELETE
            produces =MediaType.APPLICATION_JSON_VALUE,
            params = {"size","page","sortProperty","sortDirection"})
    public Page<Artist> findAll(@RequestParam(value = "page",defaultValue = "0") Integer page ,
                                @RequestParam(value="size", defaultValue = "10")Integer size,
                                @RequestParam(value = "sortProperty", defaultValue = "name") String sortProperty,
                                @RequestParam(value = "sortDirection", defaultValue = "ASC") String sortDirection)
    {
        PageRequest pageRequest = PageRequest.of(page,size, Sort.Direction.fromString(sortDirection),sortProperty);
        return artisteRepository.findAll(pageRequest);
    }

    //CREATION D'UN EMPLOYE
    @RequestMapping(
            value="",
            method = RequestMethod.POST, //Méthode HTTP : GET/POST/PATCH/PUT/DELETE
            consumes = MediaType.APPLICATION_JSON_VALUE, //Type MIME des données passées avec la requête : JSON, XML, Texte...
            produces =MediaType.APPLICATION_JSON_VALUE)
    public Artist createArtist(
            @RequestBody Artist artist){
        return artisteRepository.save(artist);
    }

    //MODIFICATION ARTIST
    @RequestMapping(
            value="{id}",
            method = RequestMethod.PUT, //Méthode HTTP : GET/POST/PATCH/PUT/DELETE
            consumes = MediaType.APPLICATION_JSON_VALUE, //Type MIME des données passées avec la requête : JSON, XML, Texte...
            produces =MediaType.APPLICATION_JSON_VALUE)
    public Artist updateArtist(
            @RequestBody Artist artist,@PathVariable int id){
        return artisteRepository.save(artist);
    }

    //DELETE ARTIST
    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteArtist (@PathVariable("id") int id) {
        artisteRepository.deleteById(id);
    }


}
