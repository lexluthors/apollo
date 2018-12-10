package com.apecoder.apollo;

import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@MapperScan("com.apecoder.apollo.mapper")
@EnableJpaAuditing
@SpringBootApplication
public class ApolloApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApolloApplication.class, args);
    }

    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }

}
