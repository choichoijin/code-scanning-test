package com.md.actionspringboot.actionItems.controller;

import com.md.actionspringboot.actionItems.dto.GetItemDTO;
import com.md.actionspringboot.actionItems.dto.UpdateItemDTO;
import com.md.actionspringboot.actionItems.service.ActionItemsService;
import com.md.actionspringboot.common.dto.PasswordDTO;
import com.md.actionspringboot.common.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
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
