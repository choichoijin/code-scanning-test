package com.md.actionspringboot.utils;

import lombok.*;

@Data
@Builder
public class GlobalResponse {
    private final String status;
    private final String message;
    private final Object data;
}
