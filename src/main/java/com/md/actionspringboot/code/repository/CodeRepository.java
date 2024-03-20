package com.md.actionspringboot.code.repository;

import com.md.actionspringboot.code.entity.Code;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeRepository extends JpaRepository<Code, Long> {
}
