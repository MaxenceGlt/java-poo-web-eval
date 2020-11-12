package com.java.eval.web.controller;

import com.java.eval.web.model.Album;
import com.java.eval.web.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/albums")
public class AlbumController {

    @Autowired
    private AlbumRepository albumRepository;

    // AJOUT ALBUM
    @RequestMapping(
            value="",
            method = RequestMethod.POST, //Méthode HTTP : GET/POST/PATCH/PUT/DELETE
            consumes = MediaType.APPLICATION_JSON_VALUE, //Type MIME des données passées avec la requête : JSON, XML, Texte...
            produces =MediaType.APPLICATION_JSON_VALUE)
    public Album createAlbum(
            @RequestBody Album album){
        return albumRepository.save(album);
    }

    //DELETE ARTIST
    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAlbum (@PathVariable("id") int id) {
        albumRepository.deleteById(id);
    }
}
