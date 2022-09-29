package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itheima.reggie.common.BaseContext;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.ShoppingCart;
import com.itheima.reggie.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author wyz-PC
 */
@Slf4j
@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 添加购物车
     * @param shoppingCart 表中数据
     * @return 客户端
     */
    @PostMapping("/add")
    @ResponseBody
    public R<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart) {
        log.info("购物车数据：{}",shoppingCart);
        //设置用户id，指定当前购物车是谁的
        Long currentId = BaseContext.getCurrentId();
        shoppingCart.setUserId(currentId);
        //查询当前菜品是否在购物车中
        Long dishId = shoppingCart.getDishId();
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,currentId);
        if (dishId != null){
            //菜品
            queryWrapper.eq(ShoppingCart::getDishId,dishId);
        } else {
            //套餐
            queryWrapper.eq(ShoppingCart::getSetmealId,shoppingCart.getSetmealId());
        }
        ShoppingCart cartServiceOne = shoppingCartService.getOne(queryWrapper);

        if (cartServiceOne != null){
            //如果已经存在就在元数据上加一
            Integer number = cartServiceOne.getNumber();
            cartServiceOne.setNumber(number + 1);
            shoppingCartService.updateById(cartServiceOne);
        } else {
            //不存在数量默认为一
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());
            shoppingCartService.save(shoppingCart);
            cartServiceOne = shoppingCart;
        }

        return R.success(cartServiceOne);
    }

    /**
     * 减少购物车内物品
     * @param shoppingCart
     * @return
     */
    @PostMapping("/sub")
    @ResponseBody
    public R<ShoppingCart> sub(@RequestBody ShoppingCart shoppingCart){
        //设置用户id，指定当前购物车是谁的
        Long currentId = BaseContext.getCurrentId();
        shoppingCart.setUserId(currentId);
        //查询当前菜品是否在购物车中
        Long dishId = shoppingCart.getDishId();
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,currentId);
        if (dishId != null){
            //菜品
            queryWrapper.eq(ShoppingCart::getDishId,dishId);
        } else {
            //套餐
            queryWrapper.eq(ShoppingCart::getSetmealId,shoppingCart.getSetmealId());
        }
        ShoppingCart cartServiceOne = shoppingCartService.getOne(queryWrapper);

        if (cartServiceOne != null){
            //如果已经存在就在元数据上减一
            Integer number = cartServiceOne.getNumber();
            cartServiceOne.setNumber(number - 1);
            shoppingCartService.updateById(cartServiceOne);
        } else {
            //不存在数量默认为0
            shoppingCart.setNumber(0);
            shoppingCart.setCreateTime(LocalDateTime.now());
            shoppingCartService.save(shoppingCart);
            cartServiceOne = shoppingCart;
        }

        return R.success(cartServiceOne);
    }

    /**
     * 查看购物车
     * @return 购物车信息
     */
    @GetMapping("/list")
    public R<List<ShoppingCart>> list() {
        log.info("查看购物车..");
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,BaseContext.getCurrentId());
        queryWrapper.orderByAsc(ShoppingCart::getCreateTime);

        List<ShoppingCart> list = shoppingCartService.list(queryWrapper);

        return R.success(list);
    }

    /**
     * 清空购物车
     */
    @DeleteMapping("/clean")
    public R<String> clean(){
        log.info("清空购物车..");
        //SQL:delete from shopping_cart where user_id = ?
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        //get user id
        queryWrapper.eq(ShoppingCart::getUserId,BaseContext.getCurrentId());

        shoppingCartService.remove(queryWrapper);

        return R.success("购物车已清空");
    }
}
