package edu.com.catolica.products.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    private String message;

    private Integer statusCode;
}
