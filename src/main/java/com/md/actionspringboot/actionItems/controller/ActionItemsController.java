package com.md.actionspringboot.actionItems.controller;

import com.md.actionspringboot.actionItems.dto.CreateItemDto;
import com.md.actionspringboot.actionItems.service.ActionItemsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/action-items")
public class ActionItemsController {
    private final ActionItemsService actionItemsService;
    @GetMapping("")
    public ResponseEntity<?> getTest() {
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping
    public ResponseEntity register(@RequestBody @Valid CreateItemDto createItemDto){
        actionItemsService.register(createItemDto);
        return ResponseEntity.ok().build();
    }
}
