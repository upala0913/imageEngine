package com.upala.wong;

import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Administrator
 */

@SpringBootApplication
@Log4j2
@MapperScan("com.upala.wong.mapper")
public class ImageEngine {
    public static void main(String[] args) {
        log.info("启动ImageEngine");
        SpringApplication.run(ImageEngine.class, args);
        log.info("启动ImageEngined");
    }
}
