package com.java.eval.web.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "album")
public class Album {

    @ManyToOne
    @JoinColumn(name = "artistId")
    private Artist artist;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    public Album(){

    }

    public Album(String title){
        this.title=title;
    }


    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return Objects.equals(artist, album.artist) &&
                Objects.equals(id, album.id) &&
                Objects.equals(title, album.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artist, id, title);
    }

    @Override
    public String toString() {
        return "Album{" +
                "title='" + title + '\'' +
                '}';
    }
}
