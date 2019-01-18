package com.baizhi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories("com.baizhi.dao.es")
@MapperScan("com.baizhi.dao.basic")
public class EsSpringbootTsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsSpringbootTsApplication.class, args);
    }
}

