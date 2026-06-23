package org.scoula.bootstudy.service;

import lombok.RequiredArgsConstructor;
import org.scoula.bootstudy.dto.BoardCreateRequest;
import org.scoula.bootstudy.dto.BoardResponse;
import org.scoula.bootstudy.dto.BoardUpdateRequest;
import org.scoula.bootstudy.repository.BoardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<BoardResponse> findAll() {
        return boardRepository.findAll();
    }

    public BoardResponse findById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다."));
    }

    public BoardResponse create(BoardCreateRequest request) {
        // insert 후 생성된 id를 받아서 다시 조회하면, created_at 같은 DB 기본값까지 응답에 담을 수 있음
        Long id = boardRepository.save(request);
        return findById(id);
    }

    public BoardResponse update(Long id, BoardUpdateRequest request) {
        int updatedCount = boardRepository.update(id, request);
        if (updatedCount == 0) {        // 예외처리
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "수정할 게시글을 찾을 수 없습니다.");
        }
        return findById(id);
    }

    public void delete(Long id) {
        int deletedCount = boardRepository.deleteById(id);
        if (deletedCount == 0) {                // 예외처리
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "삭제할 게시글을 찾을 수 없습니다.");
        }
    }
}
