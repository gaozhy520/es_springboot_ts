package com.baizhi.service;

import com.baizhi.dao.basic.PoetryDAO;
import com.baizhi.entity.Poetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoetryServiceImpl implements PoetryService{

    @Autowired
    private PoetryDAO poetryDAO;

    @Override
    public List<Poetry> queryAll() {
        return poetryDAO.findAll();
    }
}
