package com.md.actionspringboot.actionItems.dto;

import com.md.actionspringboot.actionItems.entity.ActionItems;
import com.md.actionspringboot.utils.SharedYnEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
public class UpdateItemDTO {
    private String sharedYn;
    private String type;
    private String status;
    private String title;
    private String body;
    private String dueDate;
}
