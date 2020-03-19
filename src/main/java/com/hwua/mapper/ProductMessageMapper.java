package com.hwua.mapper;

import com.hwua.pojo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductMessageMapper {
    //查询所有的旅游信息
    public List<Product> queryAllProducts() throws Exception;
    //添加一条旅游信息
    public int addProduct(@Param("productNum") String productNum,@Param("productName") String productName,@Param("departureTime") String departureTime,@Param("cityName") String cityName,
                          @Param("productPrice") Double productPrice,@Param("productDesc")String productDesc,@Param("productStatus")Integer productStatus) throws Exception;
    public List<Product> queryProductDetailById(Integer id) throws Exception;
    //通过订单的id获得游客的id
    public List<OrderTraveller> getTravellerIdByOrderId(Integer orderId);
    //查询订单管理的订单信息
    public Product queryDetailsByOrderId(Integer orderId) throws Exception;
    //查询订单管理游客的信息
    public Traveller queryTravellerByTravellerId(Integer travellerId) throws Exception;
    //查询订单管理联系人的信息
    public Orders queryMemberByOrderId(Integer orderId) throws Exception;
    //查询订单管理
    public List<Product> queryAllOrders() throws Exception;
    //通过产品id修改Status
    public int updateStatusById(@Param("ids") Integer[] ids,@Param("productStatus") Integer productStatus) throws Exception;
    //通过产品id删除产品信息
    public int deleteProductById(@Param("ids") Integer[] ids) throws Exception;
}
