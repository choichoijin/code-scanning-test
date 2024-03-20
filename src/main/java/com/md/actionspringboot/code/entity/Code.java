package com.md.actionspringboot.code.entity;

import com.md.actionspringboot.groupCode.entity.GroupCode;
import com.md.actionspringboot.utils.SharedYnEnum;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "code")
@Data
@IdClass(CodeId.class)
public class Code {

    @Id
    @ManyToOne
    @JoinColumn(name = "group_code_id", referencedColumnName = "group_code_id")
    private GroupCode groupCodeId;

    @Id
    @Column(name = "code_value")
    private Long codeValue;

    @Column(name = "code_name")
    private String codeName;

}
