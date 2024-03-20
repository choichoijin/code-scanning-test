package com.md.actionspringboot.actionItems.entity;

import com.md.actionspringboot.code.entity.Code;
import com.md.actionspringboot.code.entity.CodeId;
import com.md.actionspringboot.utils.SharedYnEnum;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "action_items")
public class ActionItems {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "action_id")
    private Long actionId;

    @Column(name = "shared_yn")
    @Enumerated(EnumType.STRING)
    private SharedYnEnum sharedYn;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "type_group_code_id", referencedColumnName = "groupCodeId"),
            @JoinColumn(name = "type_code_value", referencedColumnName = "code_value")
    })
    private Code typeCodeId;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "status_group_code_id", referencedColumnName = "groupCodeId"),
            @JoinColumn(name = "status_code_value", referencedColumnName = "code_value")
    })
    private Code statusCodeId;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    @Nullable
    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "password")
    private String password;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
