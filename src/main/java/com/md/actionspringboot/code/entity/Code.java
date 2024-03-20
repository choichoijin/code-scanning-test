package com.md.actionspringboot.code.entity;

import com.md.actionspringboot.groupCode.entity.GroupCode;
import com.md.actionspringboot.utils.SharedYnEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "code")
@IdClass(CodeId.class)
public class Code {

    @Id
    @ManyToOne
    @JoinColumn(name = "groupCodeId")
    private GroupCode groupCodeId;

    @Id
    @Column(name = "code_value")
    private Long codeValue;

    @Column(name = "code_name")
    private String codeName;

}
