package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.entity.User;

/**
 * @author wyz-PC
 */
public interface UserService extends IService<User> {
    /**
     * 发送邮件
     * @param to 发给谁
     * @param subject 发件人
     * @param text 发的内容
     */
    public void sendMsg(String to,String subject,String text);
}

