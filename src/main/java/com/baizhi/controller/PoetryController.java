package com.baizhi.controller;

import com.baizhi.dao.es.CustomPoetryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class PoetryController {

    @Autowired
    private CustomPoetryRepository customPoetryRepository;

    @RequestMapping("/search")
    public ModelAndView search(ModelAndView modelAndView, String keyword, @RequestParam(defaultValue = "1") Integer nowPage, @RequestParam(defaultValue = "5") Integer pageSize){
        Map<String, Object> map = customPoetryRepository.findByKeyWord(keyword, nowPage, pageSize);
        modelAndView.setViewName("result");
        modelAndView.addObject("map",map);
        return modelAndView;
    }
}
