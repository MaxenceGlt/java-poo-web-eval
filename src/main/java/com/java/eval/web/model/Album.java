package com.java.eval.web.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "album")
public class Album {

    @ManyToOne
    @JoinColumn(name = "ArtistId")
    private Artist artist;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer albumId;

    private String title;

    public Album(){

    }

    public Album(String title){
        this.title=title;
    }

    public Artist getArtiste() {
        return artist;
    }

    public void setArtiste(Artist artist) {
        this.artist = artist;
    }

    public Integer getId() {
        return albumId;
    }

    public void setId(Integer id) {
        this.albumId = id;
    }

    public String getNomArtiste() {
        return title;
    }

    public void setNomArtiste(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return Objects.equals(artist, album.artist) &&
                Objects.equals(albumId, album.albumId) &&
                Objects.equals(title, album.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artist, albumId, title);
    }

    @Override
    public String toString() {
        return "Album{" +
                "artist=" + artist.getName() +
                ", albumId=" + albumId +
                ", title='" + title + '\'' +
                '}';
    }
}
