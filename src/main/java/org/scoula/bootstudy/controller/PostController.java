package org.scoula.bootstudy.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.scoula.bootstudy.dto.PostCreateRequest;
import org.scoula.bootstudy.dto.PostResponse;
import org.scoula.bootstudy.dto.PostUpdateRequest;
import org.scoula.bootstudy.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponse> create(
            @Valid @RequestBody PostCreateRequest request
    ) {
        PostResponse response = postService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public PostResponse findOne(@PathVariable Long id) {
        return postService.findOne(id);
    }

    @PutMapping("/{id}")
    public PostResponse update(
            @PathVariable Long id,
            @Valid @RequestBody PostUpdateRequest request
    ) {
        return postService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }
}