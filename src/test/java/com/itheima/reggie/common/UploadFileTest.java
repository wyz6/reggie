package com.itheima.reggie.common;

import org.junit.jupiter.api.Test;

/**
 * @author wyz-PC
 * 测试文件名
 */
public class UploadFileTest {
    @Test
    public void fileTest() {
        String fileName = "ererewe.jpg";
        String suffix =  fileName.substring(fileName.lastIndexOf("."));
        System.out.println(suffix);
    }
}
