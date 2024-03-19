package com.md.actionspringboot.actionItems.entity;

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
@Table(name = "action_items")
public class ActionItems {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test")
    private Long actionItemsId;

}
