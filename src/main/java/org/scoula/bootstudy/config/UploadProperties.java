package org.scoula.bootstudy.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "upload")
public class UploadProperties {
    private String path;
    private String maxSize;
    private List<String> allowedTypes;
}