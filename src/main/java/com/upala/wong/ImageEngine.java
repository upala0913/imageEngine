package com.upala.wong;

import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author Administrator
 */

@SpringBootApplication
@Log4j2
@MapperScan("com.upala.wong.mapper")
public class ImageEngine extends SpringBootServletInitializer {

    public static void main(String[] args) {
        log.info("启动ImageEngine");
        SpringApplication.run(ImageEngine.class, args);
        log.info("启动ImageEngined");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ImageEngine.class);
    }
}
