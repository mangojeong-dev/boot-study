package org.scoula.bootstudy.controller;

import lombok.RequiredArgsConstructor;
import org.scoula.bootstudy.config.UploadProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class UploadConfigController {

    private final UploadProperties uploadProperties;

    @GetMapping("/config/upload")
    public UploadProperties uploadConfig() {
        return uploadProperties;
    }
}
