package com.md.actionspringboot.actionItems.dto;

import com.md.actionspringboot.actionItems.entity.ActionItems;
import lombok.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Builder
@Getter
@Setter
public class GetItemDTO {
    private String sharedYn;
    private String type;
    private String status;
    private String title;
    private String body;
    private String dueDate;


    public static GetItemDTO generateFromEntities(ActionItems actionItem) {
        GetItemDTO getItemDTO = GetItemDTO
                .builder()
                .sharedYn(actionItem.getSharedYn().toString())
                .type(actionItem.getTypeCodeId().getCodeName())
                .status(actionItem.getStatusCodeId().getCodeName())
                .title(actionItem.getTitle())
                .body(actionItem.getBody())
                .build();

        // Handle optional properties
        if (actionItem.getDueDate() != null) {
            getItemDTO.setDueDate(actionItem.getDueDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }

        return getItemDTO;
    }
}
