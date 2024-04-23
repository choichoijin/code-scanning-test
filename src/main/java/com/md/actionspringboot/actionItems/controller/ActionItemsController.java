package com.md.actionspringboot.actionItems.controller;

import com.md.actionspringboot.actionItems.dto.CreateItemDto;
import com.md.actionspringboot.actionItems.dto.GetItemDTO;
import com.md.actionspringboot.actionItems.dto.UpdateItemDTO;
import com.md.actionspringboot.actionItems.service.ActionItemsService;
import com.md.actionspringboot.common.dto.PasswordDTO;
import com.md.actionspringboot.common.dto.ResponseDTO;
import com.md.actionspringboot.utils.GlobalResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

@Slf4j
@CrossOrigin
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

    @GetMapping("/{actionId}")
    public ResponseEntity<Object> getActionItem(@PathVariable Long actionId) {
        try {
            GetItemDTO getItemDTO = actionItemsService.getActionItem(actionId);
            return ResponseEntity.ok(getItemDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

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

    @GetMapping("/presignedURL")
    public String generatePresignedURL(@RequestParam("uuid") String uuid){
        return actionItemsService.invokeLambdaForPresignedURL("arn:aws:lambda:ap-northeast-2:975050279870:function:generateActionBucketPresignedURL", uuid);
    }

    //code scanning test
    @DeleteMapping("/deleteFile")
    public ResponseEntity<String> deleteFile(@RequestParam("filePath") String filePath) {
        try {
            File fileToDelete = new File(filePath);
            if (fileToDelete.exists()) {
                if (fileToDelete.delete()) {
                    return ResponseEntity.ok("File deleted successfully");
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete file");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found");
            }
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Security exception occurred");
        }
    }

    @GetMapping("/log")
    public ResponseEntity<String> log(@RequestParam("bucketName") String bucketName) {
        String awsKey = "12345678";
        log.info(awsKey);
        return ResponseEntity.ok("aws key logged");
    }

    // 취약한 사용자 인증 메서드: 사용자 이름과 암호를 평문으로 받아들임
    @PostMapping("/login")
    public String login(@RequestBody String credentials) {
        // 로그인 로직은 생략되었지만, 여기서는 credentials를 평문으로 받아들임
        return "Logged in successfully";
    }

    // 취약한 데이터베이스 쿼리 메서드: 사용자 입력을 그대로 쿼리로 실행함
    @PostMapping("/queryDatabase")
    public String queryDatabase(@RequestBody String query) {
        // 사용자 입력인 query를 그대로 데이터베이스에 전달하여 실행함
        return executeQuery(query);
    }

    private String executeQuery(String query) {
        // 실제 데이터베이스 쿼리 실행 로직은 생략되었지만, 여기서는 query를 그대로 실행함
        return "Query executed: " + query;
    }

   private String executeQuery2(String query) {
        // 실제 데이터베이스 쿼리 실행 로직은 생략되었지만, 여기서는 query를 그대로 실행함
        return "Query executed: " + query;
    }

    // 취약한 셸 명령 실행 메서드: 사용자 입력을 그대로 셸 명령어로 실행함
    @PostMapping("/executeCommand")
    public String executeCommand(@RequestBody String command) {
        try {
            // 취약한 방식으로 셸 명령어를 실행함
            Process process = Runtime.getRuntime().exec(command);

            // 셸 명령어 실행 결과를 읽어들임
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // 셸 명령어 실행 결과를 반환함
            return output.toString();
        } catch (Exception e) {
            return "Error executing command: " + e.getMessage();
        }
    }

    // 취약한 객체 역직렬화 메서드: 직렬화된 데이터를 수신하여 객체로 역직렬화함
    @PostMapping("/deserializeObject")
    public String deserializeObject(@RequestBody byte[] serializedData) {
        try {
            // 직렬화된 데이터를 역직렬화하여 객체로 변환함
            Object obj = SerializationUtils.deserialize(serializedData);

            // 객체를 문자열로 변환하여 반환함 (여기서는 예시로 Map을 사용함)
            return obj.toString();
        } catch (Exception e) {
            return "Error deserializing object: " + e.getMessage();
        }
    }

        // 취약한 객체 역직렬화 메서드: 직렬화된 데이터를 수신하여 객체로 역직렬화함
    @PostMapping("/deserializeObject")
    public String deserializeObject2(@RequestBody byte[] serializedData) {
        try {
            // 직렬화된 데이터를 역직렬화하여 객체로 변환함
            Object obj = SerializationUtils.deserialize(serializedData);

            // 객체를 문자열로 변환하여 반환함 (여기서는 예시로 Map을 사용함)
            return obj.toString();
        } catch (Exception e) {
            return "Error deserializing object: " + e.getMessage();
        }
    }

    // 직렬화 유틸리티 클래스 (실제로는 Apache Commons SerializationUtils를 사용하지 않아야 함)
    private static class SerializationUtils {
        public static Object deserialize(byte[] serializedData) throws Exception {
            // 취약한 방식으로 직렬화된 데이터를 역직렬화함
            java.io.ObjectInputStream in = new java.io.ObjectInputStream(new java.io.ByteArrayInputStream(serializedData));
            return in.readObject();
        }
    }
}
