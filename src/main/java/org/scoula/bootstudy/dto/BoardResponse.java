package org.scoula.bootstudy.dto;

import java.time.LocalDateTime;

// 클라이언트에게 내려줄 게시글 응답 DTO다.
// DB 테이블 컬럼을 그대로 노출하기보다, 응답에 필요한 모양을 따로 정의하는 습관이 좋다.
public record BoardResponse(
        Long id,
        String title,
        String content,
        String writer,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
