
package com.gyjian.exception.controller;


import com.gyjian.exception.common.MyBindException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;


@RestController
public class TestController {


    private final static Integer MAX_USER_ADDR = 10;


    /**
     * 新增用户订单配送地址
     */
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        Random random = new Random();
        int i = random.nextInt();
        System.out.println(i);

        if (i >= MAX_USER_ADDR) {
            // 测试点，抛出一个异常
            throw new MyBindException("xinli.address.add.limit");
        }

        return ResponseEntity.ok("xinli.address.added.successfully");
    }


}
