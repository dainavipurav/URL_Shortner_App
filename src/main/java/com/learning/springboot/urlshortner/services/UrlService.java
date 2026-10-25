package com.learning.springboot.urlshortner.services;


public interface UrlService {
    String getShortUrl(String longUrl);

    String getLongUrl(String shortUrlId);
}
