package com.md.actionspringboot.actionItems.dto;

import com.md.actionspringboot.utils.SharedYnEnum;
import jakarta.validation.constraints.*;

import lombok.*;

import java.time.LocalDate;

@Data
public class CreateItemDto {
    @NotNull(message = "공유 여부를 선택하세요.")
    private SharedYnEnum sharedYn;

    @NotBlank(message = "구분을 선택하세요.")
    private String typeCodeName;

    private String statusCodeName;

    @NotBlank(message = "제목을 입력하세요.")
    @Size(max = 100, message = "제목은 최대 100자까지 입력 가능합니다.")
    private String title;

    @NotBlank(message = "내용을 입력하세요.")
    @Size(max = 1000, message = "내용은 최대 1000자까지 입력 가능합니다.")
    private String body;

    @FutureOrPresent(message = "오늘 이후의 날짜를 선택하세요.")
    private LocalDate dueDate;

    @NotBlank(message = "비밀번호를 입력하세요.")
    private String password;
}
