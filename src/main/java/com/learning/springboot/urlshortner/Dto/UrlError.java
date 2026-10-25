package com.learning.springboot.urlshortner.Dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
public class UrlError {
    private int status;
    private String error;
    private String message;
    private long timestamp;
}
