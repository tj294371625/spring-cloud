package com.chinadaas.service.impl;

import com.chinadaas.exception.ConnectionException;
import com.chinadaas.exception.TimeoutException;
import com.chinadaas.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author lawliet
 * @version 1.0.0
 * @description TODO
 * @createTime 2021.11.22
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public String ok(String id) {
//        int a = 1/0;
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "id: " + id + ", 调用商品服务成功";
    }

    @Override
    public String timeout(String id) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "id: " + id + ", 调用商品服务成功";
    }

    @Override
    public String errorWithHystrix(String id) {
        if (Integer.parseInt(id) < 0) {
            throw new RuntimeException();
        }

        return "id: " + id + ", 调用商品服务成功";
    }

    @Override
    public String threadIsolation(String id) {
        return "id: " + id + ", 调用商品服务成功";
    }

    @Override
    public String feignWithHystrix(String id) {
        if ("1".equals(id)) {
            throw new TimeoutException("数据库访问超时.");
        } else if ("2".equals(id)) {
            throw new ConnectionException("数据库连接异常.");
        } else if ("3".equals(id)) {
            throw new RuntimeException();
        } else {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "id: " + id + ", 调用商品服务成功";
        }
    }
}
