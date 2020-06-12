package com.lindl.mall;

import com.lindl.mall.common.exception.ExceptionFactory;
import com.lindl.mall.common.exception.ExceptionMsg;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin //
@SpringBootApplication
public class MallApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallApplication.class, args);
    }

    @Bean
    public ExceptionFactory exceptionFactory() {
        return new ExceptionFactory(new ExceptionMsg());
    }

}
