package com.md.actionspringboot.actionItems.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/action-items")
public class ActionItemsController {
    @GetMapping("")
    public ResponseEntity<?> getTest() {
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
