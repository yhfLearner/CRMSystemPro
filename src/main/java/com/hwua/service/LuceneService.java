package com.hwua.service;

import com.hwua.pojo.Product;

import java.util.List;

public interface LuceneService {

    public void createIndexDb() throws Exception;

    public List<Product> queryAllProduct(String term, Integer count) throws Exception;
}
