package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.entity.Category;

/**
 * @author wyz-PC
 * @Email wyz18405583620@163.com
 * @date 2022/9/27
 */
public interface CategoryService extends IService<Category> {
    public void remove(Long ids);
}
