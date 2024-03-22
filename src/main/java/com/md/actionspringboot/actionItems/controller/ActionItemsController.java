package com.md.actionspringboot.actionItems.controller;

import com.md.actionspringboot.actionItems.dto.GetItemDTO;
import com.md.actionspringboot.actionItems.dto.UpdateItemDTO;
import com.md.actionspringboot.actionItems.service.ActionItemsService;
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

    @CrossOrigin
    @GetMapping("/{actionId}")
    public ResponseEntity<Object> getActionItem(@PathVariable Long actionId) {
        try {
            GetItemDTO getItemDTO = actionItemsService.getActionItem(actionId);
            return ResponseEntity.ok(getItemDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @CrossOrigin
    @PatchMapping("/{actionId}")
    public ResponseEntity<Object> updateActionItem(@PathVariable Long actionId, @RequestBody UpdateItemDTO updateItemDTO) {
        try {
            actionItemsService.updateActionItem(actionId, updateItemDTO);
            return ResponseEntity.ok("Action Item 수정이 완료 되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
