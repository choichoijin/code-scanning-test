package com.md.actionspringboot.actionItems.controller;

import com.md.actionspringboot.actionItems.dto.CreateItemDto;
import com.md.actionspringboot.actionItems.dto.UpdateItemDTO;
import com.md.actionspringboot.actionItems.service.ActionItemsService;
import com.md.actionspringboot.utils.GlobalResponse;
import jakarta.validation.Valid;
import com.md.actionspringboot.actionItems.dto.GetItemDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/action-items")
public class ActionItemsController {
    private final ActionItemsService actionItemsService;
    @CrossOrigin
    @GetMapping("")
    public ResponseEntity<?> getTest() {
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @CrossOrigin
    @PostMapping
    public GlobalResponse register(@RequestBody @Valid CreateItemDto createItemDto, BindingResult bindingResult) throws BindException {
            if (bindingResult.hasErrors()){
                throw new BindException(bindingResult);
            }
            actionItemsService.register(createItemDto);
            return GlobalResponse.builder()
                    .status("success")
                    .message("Action Item 등록 성공")
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
    @CrossOrigin
    @GetMapping("/presignedURL")
    public String generatePresignedURL(@RequestParam("uuid") String uuid){
        return actionItemsService.invokeLambdaForPresignedURL("arn:aws:lambda:ap-northeast-2:975050279870:function:generateActionBucketPresignedURL",uuid);
    }
}
