package org.scoula.bootstudy.service;

import lombok.RequiredArgsConstructor;
import org.scoula.bootstudy.dto.Post;
import org.scoula.bootstudy.dto.PostCreateRequest;
import org.scoula.bootstudy.dto.PostResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class PostService {

    private final Map<Long, PostResponse> store = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);

    public PostResponse create(PostCreateRequest request) {
        Long id = sequence.incrementAndGet();
        LocalDateTime now = LocalDateTime.now();
        PostResponse response = new PostResponse(
                id,
                request.title(),
                request.content(),
                now,
                now
        );
        store.put(id, response);
        return response;
    }

    public PostResponse findOne(Long id) {
        PostResponse response = store.get(id);
        if (response == null) {
            throw new ResourceNotFoundException("게시글을 찾을 수 없습니다. id=" + id);
        }
        return response;
    }


}