package com.md.actionspringboot.actionItems.service;

import com.md.actionspringboot.actionItems.dto.GetItemDTO;
import com.md.actionspringboot.actionItems.dto.UpdateItemDTO;
import com.md.actionspringboot.actionItems.dto.CreateItemDto;
import com.md.actionspringboot.actionItems.entity.ActionItems;
import com.md.actionspringboot.actionItems.repository.ActionItemsRepository;
import com.md.actionspringboot.code.entity.Code;
import com.md.actionspringboot.code.entity.CodeId;
import com.md.actionspringboot.utils.SharedYnEnum;
import jakarta.transaction.Transactional;
import com.md.actionspringboot.code.repository.CodeRepository;

import com.md.actionspringboot.groupCode.repository.GroupCodeRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;
import software.amazon.awssdk.services.lambda.model.InvokeResponse;
import software.amazon.awssdk.services.lambda.model.LambdaException;

import java.time.Duration;
import java.util.Map;
import java.util.Optional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Service
@RequiredArgsConstructor
@Transactional
public class ActionItemsService {
    private final ActionItemsRepository actionItemsRepository;
    private final CodeRepository codeRepository;

    private final LambdaClient lambdaClient;

    public Long register(CreateItemDto createItemDto) {
        String typeCodeName = createItemDto.getType();
        String statusCodeName = createItemDto.getStatus();

        Optional<Code> optionalType = codeRepository.findByCodeName(typeCodeName);
        Optional<Code> optionalStatus = codeRepository.findByCodeName(statusCodeName);

        if (optionalType.isEmpty()) {
            throw new RuntimeException("존재하지 않는 구분명입니다.");
        }

        if (optionalStatus.isEmpty()) {
            throw new RuntimeException("존재하지 않는 상태명입니다.");
        }
        Code type = optionalType.get();
        Code status = optionalStatus.get();

        ActionItems actionItem = ActionItems.builder()
                .sharedYn(createItemDto.getSharedYn())
                .typeCodeId(type)
                .statusCodeId(status)
                .title(createItemDto.getTitle())
                .body(createItemDto.getBody())
                .dueDate(createItemDto.getDueDate())
                .password(createItemDto.getPassword())
                .build();

        actionItemsRepository.saveAndFlush(actionItem);
        return actionItem.getActionId();
    }

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

        actionItemsRepository.saveAndFlush(actionItem);

    }
    public String invokeLambdaForPresignedURL(String functionName){
        String result = null;
        try{
            String json = "{\"key\":\"testing\"}";
            SdkBytes payload = SdkBytes.fromUtf8String(json);
            InvokeRequest request = InvokeRequest.builder()
                    .functionName(functionName)
                    .payload(payload)
                    .build();

            InvokeResponse preSignedURL = lambdaClient.invoke(request);
            result = preSignedURL.payload().asUtf8String();
            return result;
        }catch (LambdaException e){
            System.err.println(e.getMessage());
            return result;
        }
    }
}
