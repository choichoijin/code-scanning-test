package com.md.actionspringboot.actionItems.controller;

import com.md.actionspringboot.actionItems.dto.CreateItemDto;
import com.md.actionspringboot.actionItems.service.ActionItemsService;
import com.md.actionspringboot.utils.GlobalResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            if (bindingResult.hasErrors()){
                throw new BindException(bindingResult);
            }
            actionItemsService.register(createItemDto);
            return GlobalResponse.builder()
                    .status("success")
                    .message("Action Item 등록 성공")
                    .build();
    }

    @GetMapping("/presignedURL")
    public String generatePresignedURL(){
        return actionItemsService.invokeLambdaForPresignedURL("arn:aws:lambda:ap-northeast-2:975050279870:function:generateActionBucketPresignedURL");
    }
}
