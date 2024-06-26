package com.md.actionspringboot.actionItems.dto;

import com.md.actionspringboot.utils.SharedYnEnum;
import jakarta.validation.constraints.*;

import lombok.*;

import java.beans.Transient;
import java.time.LocalDate;
import java.util.List;

@Data
public class CreateItemDto {
    @NotNull(message = "공유 여부를 선택하세요.")
    private SharedYnEnum sharedYn;

    @NotBlank(message = "구분을 선택하세요.")
    private String type;

    private String status;

    @NotBlank(message = "제목을 입력하세요.")
    @Size(max = 100, message = "제목은 최대 100자까지 입력 가능합니다.")
    private String title;

    @NotBlank(message = "내용을 입력하세요.")
    @Size(max = 1000, message = "내용은 최대 1000자까지 입력 가능합니다.")
    private String body;

    @FutureOrPresent(message = "오늘 이후의 날짜를 선택하세요.")
    private LocalDate dueDate;

    @NotBlank(message = "비밀번호를 입력하세요.")
    @Pattern(regexp = "\\d{4}", message = "비밀번호는 숫자 4자리를 입력하세요")
    private String password;

    @NotBlank()
    private String uuid;

    @Size(max = 3)
    private List<String> attachments;
}
