package com.baizhi.dao.es;

import com.baizhi.entity.Poetry;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PoetryRepository extends ElasticsearchRepository<Poetry,Integer> {

}
