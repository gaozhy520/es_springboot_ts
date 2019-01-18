package com.baizhi;

import com.baizhi.dao.es.CustomPoetryRepository;
import com.baizhi.dao.es.PoetryRepository;
import com.baizhi.entity.Poetry;
import com.baizhi.service.PoetryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsSpringbootTsApplicationTests {
    @Autowired
    private PoetryService poetryService;
    @Autowired
    private PoetryRepository repository;
    @Autowired
    private CustomPoetryRepository customPoetryRepository;

    @Test
    public void test1() {
        List<Poetry> poetries = poetryService.queryAll();
        System.out.println(poetries.size());
    }

    @Test
    public void test2() {
        List<Poetry> poetries = poetryService.queryAll();
        repository.saveAll(poetries);
    }

    @Test
    public void test3() {
        Map<String, Object> map = customPoetryRepository.findByKeyWord("李白", 2, 2);
        map.forEach((k,v) -> {
            System.out.println(k + " " + v);
        });
    }
}

