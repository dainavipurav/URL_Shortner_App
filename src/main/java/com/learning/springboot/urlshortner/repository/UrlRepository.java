package com.learning.springboot.urlshortner.repository;

import com.learning.springboot.urlshortner.entity.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<UrlEntity, Integer> {
    UrlEntity findByShortUrl(String shortUrl);
}
