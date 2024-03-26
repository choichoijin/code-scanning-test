package com.md.actionspringboot.actionItems.service;

import com.md.actionspringboot.actionItems.dto.CreateItemDto;
import com.md.actionspringboot.actionItems.entity.ActionItems;
import com.md.actionspringboot.actionItems.repository.ActionItemsRepository;
import com.md.actionspringboot.code.entity.Code;
import com.md.actionspringboot.code.repository.CodeRepository;
import com.md.actionspringboot.groupCode.repository.GroupCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActionItemsService {
    private final ActionItemsRepository actionItemsRepository;
    private final CodeRepository codeRepository;
    private final GroupCodeRepository groupCodeRepository;

    public void register(CreateItemDto createItemDto) {
        String typeCodeName = createItemDto.getType();
        String statusCodeName = createItemDto.getStatus();

        // 코드 네임을 통한 조회
        Optional<Code> optionalType = codeRepository.findByCodeName(typeCodeName);
        Optional<Code> optionalStatus = codeRepository.findByCodeName(statusCodeName);

        if (optionalType.isEmpty()){
            throw new RuntimeException("존재하지 않는 구분명입니다.");
        }

        if (optionalStatus.isEmpty()){
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

    }
}
