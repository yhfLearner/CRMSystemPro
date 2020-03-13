package com.hwua.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hwua.log.MyLog;
import com.hwua.pojo.*;
import com.hwua.service.ProductMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.*;

@Controller
public class ProductController {
    @Autowired
    private ProductMessageService promService;

    @GetMapping("/product/allInfo")
    @MyLog
    @ResponseBody
    public PageInfo<Product> queryAllProduct( Integer pageNum, Integer pageSize) throws Exception{
        PageHelper.startPage(pageNum, pageSize);
        List<Product> productList = promService.queryAllProducts();
        PageInfo<Product> pageInfo = new PageInfo<>(productList);
        return pageInfo;
    }
    @PostMapping("/product/addPro")
    @MyLog
    public Map<String,Object> addPro(Product product,@RequestParam("departureTime") String strTme,@RequestParam("productPrice") String productPrice) throws Exception{
        Map<String,Object> map = new HashMap<>();
        System.out.println(strTme);
        product.setProductPrice(Double.parseDouble(productPrice));
        Timestamp ts = Timestamp.valueOf(strTme);
        product.setDepartureTime(ts);
        if( promService.addProduct(product)>0){
           map.put("info","成功");

       }else {
             map.put("info",null);
       }
        return map;
    }


    @GetMapping("/product/detailsPro")
    @MyLog
    @ResponseBody
    public  PageInfo<Product> queryProductDetailById( String id, Integer pageNum, Integer pageSize,Model model) throws Exception{
        PageHelper.startPage(pageNum, pageSize);
        List<Product> products = promService.queryProductDetailById(Integer.parseInt(id));
        PageInfo<Product> pageInfo = new PageInfo<>(products);
        return pageInfo;
    }

    @GetMapping("/product/queryOrders/{id}")
    @MyLog
    public String queryOrderById(@PathVariable("id") Integer id,Model model) throws Exception{
        //ModelAndView mv = new ModelAndView("pages/orders-list");
        List<Product> products = promService.queryProductDetailById(id);
        PageInfo<Product> pageInfo = new PageInfo<>(products);
        model.addAttribute("list",products);
        model.addAttribute("ordersList",pageInfo);
        //model.addAttribute("ordersList",pageInfo);
       return "pages/orders-list";
    }
    @GetMapping("/product/proAllDetails/{orderId}")
    @MyLog
    public String queryAllDetails(@PathVariable("orderId")Integer orderId,Model model) throws Exception{
        List<Traveller> travellerList = new ArrayList<>();
        Product product = promService.queryDetailsByOrderId(orderId);
        List<OrderTraveller> travellerIds = promService.getTravellerIdByOrderId(orderId);
        for (OrderTraveller travellerId : travellerIds) {
            Traveller traveller = promService.queryTravellerByTravellerId(travellerId.getTravellerId());
            travellerList.add(traveller);
        }
        Orders order = promService.queryMemberByOrderId(orderId);
        model.addAttribute("product",product);
        model.addAttribute("travellerList",travellerList);
        model.addAttribute("order",order);
        return "pages/orders-show";

    }
    @GetMapping("/product/allOrder")
    @MyLog
    public ModelAndView queryAllOrder() throws Exception{
        ModelAndView mv = new ModelAndView("pages/orders-page-list");
        List<Product> productList = promService.queryAllOrders();
        mv.addObject("productList",productList);
        return mv;
    }
    @PutMapping("/product/updateStatus")
    @MyLog
    @ResponseBody
    public Map<String,Object> updateStatus(Integer[] ids,Integer productStatus) throws Exception {
        for (Integer id : ids) {
            System.out.println(id);
        }
        System.out.println(productStatus);
        int res = promService.updateStatusById(ids, productStatus);
        Map<String, Object> map = new HashMap<>();
        if (res > 0) {
            map.put("info", "更新成功");
        } else {
            map.put("info", "更新失败");
        }
        return map;
    }
    @DeleteMapping("/product/deleteProduct")
    @MyLog
    @ResponseBody
    public Map<String,Object> deleteProductById(Integer[] ids) throws Exception{
        int res = promService.deleteProductById(ids);
        Map<String, Object> map = new HashMap<>();
        if (res > 0) {
            map.put("info", "删除成功");
        } else {
            map.put("info", "删除失败");
        }
        return map;
    }

}
