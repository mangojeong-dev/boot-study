package org.scoula.bootstudy.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.scoula.bootstudy.dto.BoardCreateRequest;
import org.scoula.bootstudy.dto.BoardResponse;
import org.scoula.bootstudy.dto.BoardUpdateRequest;
import org.scoula.bootstudy.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;

    // GET /api/boards
    // 게시글 목록을 조회한다. 목록은 Repository SQL에서 id 내림차순으로 정렬한다.
    @GetMapping
    public List<BoardResponse> findAll() {
        return boardService.findAll();
    }

    // GET /api/boards/{id}
    // URL 경로에 들어온 id를 @PathVariable로 받는다.
    @GetMapping("/{id}")
    public BoardResponse findById(@PathVariable Long id) {
        return boardService.findById(id);
    }

    // POST /api/boards
    // JSON 요청 본문을 @RequestBody로 받고, @Valid로 제목/내용/작성자 필수값 검증
    @PostMapping
    public ResponseEntity<BoardResponse> create(@Valid @RequestBody BoardCreateRequest request) {
        BoardResponse response = boardService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // PUT /api/boards/{id}
    // 전체 수정에 가까운 요청이므로 title, content를 모두 받도록
    @PutMapping("/{id}")
    public BoardResponse update(
            @PathVariable Long id,
            @Valid @RequestBody BoardUpdateRequest request
    ) {
        return boardService.update(id, request);
    }

    // DELETE /api/boards/{id}
    // 삭제 성공 시 응답 본문 없이 204 No Content를 반환한다.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boardService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
