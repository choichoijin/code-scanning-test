package com.md.actionspringboot.actionItems.service;

import com.md.actionspringboot.actionItems.dto.GetItemDTO;
import com.md.actionspringboot.actionItems.dto.UpdateItemDTO;
import com.md.actionspringboot.actionItems.entity.ActionItems;
import com.md.actionspringboot.actionItems.repository.ActionItemsRepository;
import com.md.actionspringboot.code.entity.Code;
import com.md.actionspringboot.code.entity.CodeId;
import com.md.actionspringboot.code.repository.CodeRepository;
import com.md.actionspringboot.utils.SharedYnEnum;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
@Transactional
public class ActionItemsService {

    private final ActionItemsRepository actionItemsRepository;
    private final CodeRepository codeRepository;

    public GetItemDTO getActionItem(Long actionId) throws Exception {
        ActionItems actionItem = actionItemsRepository.findById(actionId).orElse(null);

        if (actionItem == null) {
            throw new Exception("해당 ID를 가진 Action Item을 찾을 수 없습니다.");
        }

        return GetItemDTO.generateFromEntities(actionItem);
    }

    public void updateActionItem(Long actionId, UpdateItemDTO updateItemDTO) throws Exception {
        ActionItems actionItem = actionItemsRepository.findById(actionId).orElse(null);

        if (actionItem == null) {
            throw new Exception("해당 ID를 가진 Action Item을 찾을 수 없습니다.");
        }

        if (updateItemDTO.getSharedYn() != null) {
            actionItem.setSharedYn(SharedYnEnum.valueOf(updateItemDTO.getSharedYn()));
        }
        if (updateItemDTO.getType() != null) {
            Code typeCode = codeRepository.findByCodeName(updateItemDTO.getType()).orElseThrow(() ->
                    new Exception("해당하는 구분 코드가 없습니다."));
            actionItem.setTypeCodeId(typeCode);
        }
        if (updateItemDTO.getStatus() != null) {
            Code statusCode = codeRepository.findByCodeName(updateItemDTO.getStatus()).orElseThrow(() ->
                    new Exception("해당하는 상태 코드가 없습니다."));
            actionItem.setStatusCodeId(statusCode);
        }
        if (updateItemDTO.getTitle() != null) {
            actionItem.setTitle(updateItemDTO.getTitle());
        }
        if (updateItemDTO.getBody() != null) {
            actionItem.setBody(updateItemDTO.getBody());
        }
        if (updateItemDTO.getDueDate() != null) {
            actionItem.setDueDate(LocalDate.parse(updateItemDTO.getDueDate()));
        }

        actionItemsRepository.save(actionItem);
    }
}
