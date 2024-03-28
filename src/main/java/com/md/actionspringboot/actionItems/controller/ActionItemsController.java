package com.md.actionspringboot.actionItems.controller;


import com.md.actionspringboot.actionItems.dto.CreateItemDto;
import com.md.actionspringboot.actionItems.service.ActionItemsService;
import com.md.actionspringboot.utils.GlobalResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.md.actionspringboot.actionItems.dto.GetItemDTO;
import com.md.actionspringboot.actionItems.dto.UpdateItemDTO;



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
    @CrossOrigin(origins = "http://localhost:5173")
    public GlobalResponse register(@RequestBody @Valid CreateItemDto createItemDto, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        Long actionId = actionItemsService.register(createItemDto);
        return GlobalResponse.builder()
                .status("success")
                .message("Action Item이 등록되었습니다.")
                .data(actionId)
                .build();
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
