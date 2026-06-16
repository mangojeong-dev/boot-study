package org.scoula.bootstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// @SpringBootApplication은 Boot 어플리케이션 기준점이다.
// 해당 클래스가 있는 패키지 아래를 기준으로 컴포넌트 스캔 진행
@SpringBootApplication
public class BootStudyApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootStudyApplication.class, args);
    }
}
