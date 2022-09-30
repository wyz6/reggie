package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.entity.Orders;

/**
 * @author wyz-PC
 */
public interface OrderService extends IService<Orders> {

    /**
     * 用户下单
     * @param order 订单数据
     */
    public void submit(Orders order) ;
}
