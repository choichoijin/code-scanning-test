package com.md.actionspringboot.common.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDTO {
    private String status;
    private String message;
    private Object data;
}
