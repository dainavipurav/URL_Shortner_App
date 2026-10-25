package com.learning.springboot.urlshortner.controllers;

import com.learning.springboot.urlshortner.Dto.ShortUrlRequest;
import com.learning.springboot.urlshortner.services.UrlService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UrlShortnerController {

    private final UrlService urlService;

    @PostMapping("/shortUrl")
    public String getShortUrl(@RequestBody ShortUrlRequest request){
        return urlService.getShortUrl(request.getLongUrl());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> getLongUrl(@PathVariable String id){
        String longUrl = urlService.getLongUrl(id);

        return ResponseEntity.
                status(HttpStatus.FOUND)
                .location(URI.create(longUrl))
                .build();
    }
}
