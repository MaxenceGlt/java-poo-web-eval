package com.java.eval.web.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="artist")
public class Artist {

    @OneToMany(mappedBy = "artist")
    @JsonIgnoreProperties("artist")
    private Set<Album> albums = new HashSet();


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer artistId;

    private String name;

    public Artist(){

    }

    public Artist(HashSet<Album> albums, String name){
        this.albums=albums;
        this.name=name;
    }

    public Integer getId() {
        return artistId;
    }

    public void setId(Integer artistId) {
        this.artistId = artistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return artistId == artist.artistId &&
                Objects.equals(name, artist.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artistId, name);
    }

    @Override
    public String toString() {
        return "Artiste{" +
                ", artistId=" + artistId +
                ", name='" + name + '\'' +
                '}';
    }
}
