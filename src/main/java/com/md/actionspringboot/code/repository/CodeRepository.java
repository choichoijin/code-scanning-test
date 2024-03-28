package com.md.actionspringboot.code.repository;

import com.md.actionspringboot.code.entity.Code;
import com.md.actionspringboot.code.entity.CodeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CodeRepository extends JpaRepository<Code, CodeId> {
    Optional<Code> findByCodeName(String codeName);

}
