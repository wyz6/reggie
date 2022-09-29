package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.dto.SetmealDto;
import com.itheima.reggie.entity.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    /**
     * 新增套餐，同时需要保存套餐和菜品的关联
     * @param setmealDto 设置菜品数据
     */
    public void saveWithDish(SetmealDto setmealDto);

    /**
     * 删除套餐
     * @param ids 套餐id
     */
    public void removeWithDish(List<Long> ids);

    /**
     * 根据id更新套餐信息
     * @param setmealDto
     */
    public void updateByIdWithDishes(SetmealDto setmealDto);

    /**
     * 根据id组更新套餐状态
     * @param status
     * @param ids
     */
    public void updateStatusByIds(int status, Long[] ids);

    /**
     * 通过id查询套餐信息， 同时还要查询关联表setmeal_dish的菜品信息进行回显。
     *
     * @param id 待查询的id
     */
    public SetmealDto getByIdWithDish(Long id);
}
