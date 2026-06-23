package org.scoula.bootstudy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// 게시글 수정에서는 작성자(writer)는 바꾸지 않고, 제목과 내용만 수정하도록 분리했다.
// 작성 요청 DTO와 수정 요청 DTO를 나누면 API 의도가 더 명확해진다.
public record BoardUpdateRequest(
        @NotBlank(message = "제목은 필수입니다.")
        @Size(max = 200, message = "제목은 200자 이하여야 합니다.")
        String title,

        @NotBlank(message = "내용은 필수입니다.")
        String content
) {
}
