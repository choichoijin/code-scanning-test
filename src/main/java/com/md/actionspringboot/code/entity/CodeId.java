package com.md.actionspringboot.code.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class CodeId implements Serializable {

    private Long groupCodeId;
    private Long codeValue;

}
