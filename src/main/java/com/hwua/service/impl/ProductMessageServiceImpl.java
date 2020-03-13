package com.hwua.service.impl;

import com.hwua.mapper.ProductMessageMapper;
import com.hwua.pojo.*;
import com.hwua.service.ProductMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductMessageServiceImpl implements ProductMessageService {
    @Autowired
    private ProductMessageMapper productMessageMapper;
    @Override
    public List<Product> queryAllProducts() throws Exception {
        return productMessageMapper.queryAllProducts();
    }

    @Override
    public int addProduct(Product product) throws Exception {
       return productMessageMapper.addProduct(product);
    }

    @Override
    public List<Product> queryProductDetailById(Integer id) throws Exception {
        return productMessageMapper.queryProductDetailById(id);
    }

    @Override
    public List<OrderTraveller> getTravellerIdByOrderId(Integer orderId) {
        return productMessageMapper.getTravellerIdByOrderId(orderId);
    }

    @Override
    public Product queryDetailsByOrderId(Integer orderId) throws Exception {
        return productMessageMapper.queryDetailsByOrderId(orderId);
    }

    @Override
    public Traveller queryTravellerByTravellerId(Integer travellerId) throws Exception {
        return productMessageMapper.queryTravellerByTravellerId(travellerId);
    }

    @Override
    public Orders queryMemberByOrderId(Integer orderId) throws Exception {
        return productMessageMapper.queryMemberByOrderId(orderId);
    }

    @Override
    public List<Product> queryAllOrders() throws Exception {
        return productMessageMapper.queryAllOrders();
    }

    @Override
    public int updateStatusById(Integer[] ids,Integer productStatus) throws Exception {
        return productMessageMapper.updateStatusById(ids, productStatus);
    }

    @Override
    public int deleteProductById(Integer[] ids) throws Exception {
        return productMessageMapper.deleteProductById(ids);
    }

}
