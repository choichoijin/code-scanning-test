package com.md.actionspringboot.actionItems.controller;

import com.md.actionspringboot.common.dto.PasswordDTO;
import com.md.actionspringboot.actionItems.service.ActionItemsService;
import com.md.actionspringboot.common.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/action-items")
public class ActionItemsController {

    private final ActionItemsService actionItemsService;

    @DeleteMapping("/{actionId}")
    public ResponseEntity<ResponseDTO> doDeleteItems(@RequestBody PasswordDTO passwordDto,
                                                             @PathVariable Long actionId) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            responseDTO = actionItemsService.deleteActionItems(actionId, passwordDto.getPassword());
            if (responseDTO.getStatus().equals("success")) {
                return ResponseEntity.ok().body(responseDTO);
            } else {
                return ResponseEntity.badRequest().body(responseDTO);
            }
        } catch (Exception e) {
            responseDTO.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(responseDTO);
        }

    }

    @PostMapping("/{actionId}")
    public ResponseEntity<ResponseDTO> doCheckPassword(@RequestBody PasswordDTO passwordDto,
                                                       @PathVariable Long actionId) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            responseDTO = actionItemsService.checkPassword(actionId, passwordDto.getPassword());
            if (responseDTO.getStatus().equals("success")) {
                return ResponseEntity.ok().body(responseDTO);
            } else {
                return ResponseEntity.badRequest().body(responseDTO);
            }
        } catch (Exception e) {
            responseDTO.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

}
