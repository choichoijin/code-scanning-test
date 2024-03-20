package com.md.actionspringboot.code.entity;

import com.md.actionspringboot.groupCode.entity.GroupCode;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CodeId implements Serializable {

    private GroupCode groupCodeId;
    private Long codeValue;

}
