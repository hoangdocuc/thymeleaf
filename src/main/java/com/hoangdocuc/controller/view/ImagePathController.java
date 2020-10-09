package com.hoangdocuc.controller.view;


import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class ImagePathController {

    // create path for file image
    // use :. + can get all in path including after dot (.)
    @GetMapping(value = "images/{photo:.+}")
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable("photo") String photo){
        if (!photo.equals("")||photo!=null){
            try{
                Path filename = Paths.get("uploads",photo);
                byte[] buffer = Files.readAllBytes(filename);
                ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
                return ResponseEntity.ok()
                        .contentLength(buffer.length)
                        .contentType(MediaType.parseMediaType("image/png"))
                        .body(byteArrayResource);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return ResponseEntity.badRequest().build();
    }
}
