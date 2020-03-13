package com.hwua.service;

import com.hwua.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMessageService {
    public List<Product> queryAllProducts() throws Exception;
    public int addProduct(Product product) throws Exception;
    public List<Product> queryProductDetailById(Integer id) throws Exception;

    public List<OrderTraveller> getTravellerIdByOrderId(Integer orderId);
    public Product queryDetailsByOrderId(Integer orderId) throws Exception;
    public Traveller queryTravellerByTravellerId(Integer travellerId) throws Exception;
    public Orders queryMemberByOrderId(Integer orderId) throws Exception;
    public List<Product> queryAllOrders() throws Exception;
    public int updateStatusById(Integer[] ids,Integer productStatus) throws Exception;
    public int deleteProductById(Integer[] ids) throws Exception;
}
