package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.dto.DishDto;
import com.itheima.reggie.entity.Dish;

public interface DishService extends IService<Dish> {

    /**
     * 新增菜品，同时插入菜品对应的口味数据，需要操作两张表：dish、dish_flavor
     */
    public void saveWithFlavor(DishDto dishDto);

    /**
     * 查找菜品信息
     * @param id id
     * @return 菜品信息
     */
    public DishDto getByIdWithFlavor(Long id);

    /**
     * 跟新菜品信息，同时更新口味信息
     * @param dishDto 信息表
     */
    public void updateWithFlavor(DishDto dishDto);
}
