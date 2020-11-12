package com.java.eval.web;


import com.java.eval.web.model.Album;
import com.java.eval.web.repository.AlbumRepository;
import com.java.eval.web.repository.ArtisteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    ArtisteRepository artisteRepository;

    @Override
    public void run(String... strings) throws Exception {

        System.out.println(albumRepository.count());
        System.out.println(albumRepository.findByTitle("Big Ones"));

        System.out.println(albumRepository.findById(4));
        System.out.println(artisteRepository.findByName("AC/DC"));
        System.out.println(artisteRepository.findById(1));
        System.out.println(artisteRepository.findByName("aerosmith"));
        //System.out.println(artisteRepository.findListAlbum(3));
    }

    public static void print(Object t) {
        System.out.println(t);
    }
}