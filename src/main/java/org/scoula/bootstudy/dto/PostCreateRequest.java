package org.scoula.bootstudy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


// 게시글 생성 요청의 JSON body를 java 객체로 받기 위한 DTO
public record PostCreateRequest(
        @NotBlank(message = "제목은 필수입니다.")
        @Size(max = 200, message = "제목은 200자를 넘을 수 없습니다.")
        String title,

        @NotBlank(message = "내용은 필수입니다.")
        String content
) {
}