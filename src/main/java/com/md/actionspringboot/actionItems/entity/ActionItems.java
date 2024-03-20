package com.md.actionspringboot.actionItems.entity;

import com.md.actionspringboot.code.entity.Code;
import com.md.actionspringboot.code.entity.CodeId;
import com.md.actionspringboot.utils.SharedYnEnum;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "action_items")
public class ActionItems {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "action_id")
    private Long actionId;

    @Column(name = "shared_yn", columnDefinition = "CHAR(1) DEFAULT 'N'")
    @Enumerated(EnumType.STRING)
    private SharedYnEnum sharedYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "type_group_code_id", referencedColumnName = "group_code_id"),
            @JoinColumn(name = "type_code_value", referencedColumnName = "code_value")
    })
    private Code typeCodeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "status_group_code_id", referencedColumnName = "group_code_id"),
            @JoinColumn(name = "status_code_value", referencedColumnName = "code_value")
    })
    private Code statusCodeId;

    @Column(name = "title", columnDefinition = "VARCHAR(100)")
    private String title;

    @Column(name = "body", columnDefinition = "VARCHAR(1000)")
    private String body;

    @Nullable
    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "password", columnDefinition = "VARCHAR(4)")
    private String password;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
