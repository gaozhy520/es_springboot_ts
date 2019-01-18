package com.baizhi.dao.es;

import com.baizhi.entity.Poetry;

import java.util.List;
import java.util.Map;

public interface CustomPoetryRepository {

    public Map<String,Object> findByKeyWord(String keyword, Integer nowPage, Integer pageSize);
}
