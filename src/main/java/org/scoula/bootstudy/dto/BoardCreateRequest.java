package org.scoula.bootstudy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// 게시글 작성 요청으로 들어오는 JSON을 Java 객체로 받기 위한 DTO다.
// Controller는 요청 데이터를 이 객체로 받고, Service/Repository는 필요한 값만 꺼내서 사용한다.
public record BoardCreateRequest(
        @NotBlank(message = "제목은 필수입니다.")
        @Size(max = 200, message = "제목은 200자 이하여야 합니다.")
        String title,

        @NotBlank(message = "내용은 필수입니다.")
        String content,

        @NotBlank(message = "작성자는 필수입니다.")
        @Size(max = 100, message = "작성자는 100자 이하여야 합니다.")
        String writer
) {
}
