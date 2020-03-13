package com.hwua.controller;

import com.hwua.log.MyLog;
import com.hwua.pojo.Product;
import com.hwua.service.LuceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class LuceneController {
    @Autowired
    private LuceneService luceneService;
    @GetMapping("/showProduct/{term}/{count}")
    @MyLog
    public ModelAndView showProducts(@PathVariable("term") String term,@PathVariable("count") Integer count) throws Exception{
        ModelAndView mv = new ModelAndView("pages/product-list1");
        List<Product> productList = luceneService.queryAllProduct(term, count);
        mv.addObject("productList",productList);
        return mv;
    }
}
