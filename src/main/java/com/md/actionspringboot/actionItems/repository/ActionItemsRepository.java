package com.md.actionspringboot.actionItems.repository;

import com.md.actionspringboot.actionItems.entity.ActionItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionItemsRepository extends JpaRepository<ActionItems, Long> {
}
