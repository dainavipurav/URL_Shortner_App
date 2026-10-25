package com.learning.springboot.urlshortner.services.impl;

import com.learning.springboot.urlshortner.config.AppConfig;
import com.learning.springboot.urlshortner.entity.UrlEntity;
import com.learning.springboot.urlshortner.exceptions.UrlNotFoundException;
import com.learning.springboot.urlshortner.repository.UrlRepository;
import com.learning.springboot.urlshortner.services.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {

    private final AppConfig appConfig;
    private final UrlRepository repository;

    private final SecureRandom RANDOM = new SecureRandom();

    public String generate() {
        StringBuilder result = new StringBuilder(10);

        for (int i = 0; i < 10; i++) {
            result.append(
                    appConfig.getShortUrlKey().charAt(
                            RANDOM.nextInt(appConfig.getShortUrlKey().length())
                    )
            );
        }

        return result.toString();
    }

    @Override
    public String getShortUrl(String longUrl) {
        String shorURL = generate();
        repository.save(UrlEntity.builder().longUrl(longUrl).shortUrl(shorURL).build());
        return appConfig.getBaseUrl() + "/" + shorURL;
    }

    @Override
    public String getLongUrl(String shortUrlId) {
        UrlEntity urlEntity = repository.findByShortUrl(shortUrlId);
        if(urlEntity == null) {
            throw new UrlNotFoundException("No record available with " + shortUrlId +" this id");
        }

        return urlEntity.getLongUrl();
    }


}
