package com.md.actionspringboot.actionItems.service;

import com.md.actionspringboot.actionItems.entity.ActionItems;
import com.md.actionspringboot.actionItems.repository.ActionItemsRepository;
import com.md.actionspringboot.common.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class ActionItemsService {

    private final ActionItemsRepository actionItemsRepository;

    @Transactional
    public ResponseDTO deleteActionItems(Long actionId, String password) {

            Optional<ActionItems> actionItemsOptional = actionItemsRepository.findById(actionId);
            if (actionItemsOptional.isEmpty()) {
                return ResponseDTO.builder()
                        .status("fail")
                        .message("일치하는 액션 아이템이 없습니다.")
                        .build();
            }

            ActionItems actionItems = actionItemsOptional.get();
            if (!actionItems.getPassword().equals(password)) {
                return ResponseDTO.builder()
                        .status("success")
                        .message("패스워드가 일치하지 않습니다.")
                        .build();
            }

            actionItemsRepository.delete(actionItems);
            return ResponseDTO.builder()
                    .status("success")
                    .message("삭제 성공")
                    .build();

    }

    @Transactional
    public ResponseDTO checkPassword(Long actionId, String password) {

        Optional<ActionItems> actionItemsOptional = actionItemsRepository.findById(actionId);

        if (actionItemsOptional.isEmpty()) {
            return ResponseDTO.builder()
                    .status("fail")
                    .message("일치하는 액션 아이템이 없습니다.")
                    .build();
        }

        ActionItems actionItems = actionItemsOptional.get();
        boolean isPasswordMatch = actionItems.getPassword().equals(password);
        return ResponseDTO.builder()
                .status("success")
                .message("비밀번호 확인 성공")
                .data(isPasswordMatch)
                .build();

    }

}
