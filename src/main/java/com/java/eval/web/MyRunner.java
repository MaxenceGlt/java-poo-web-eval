package com.java.eval.web;


import com.java.eval.web.repository.AlbumRepository;
import com.java.eval.web.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    ArtistRepository artistRepository;

    @Override
    public void run(String... strings) throws Exception {

        System.out.println(albumRepository.count());
        //System.out.println(albumRepository.findByTitle("Big Ones"));
        System.out.println(albumRepository.findById(1));
        //System.out.println(artistRepository.findByName("AC/DC"));
        //System.out.println(artistRepository.findById(1));
        //System.out.println(artistRepository.findByName("aerosmith"));
        //System.out.println(artisteRepository.findListAlbum(3));
    }

    public static void print(Object t) {
        System.out.println(t);
    }
}