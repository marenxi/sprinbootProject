package com.itszt.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


/**
 * @Description:
 * @author: marenxi@jiuxian.com
 * @date: 2019-05-05 14:10/星期日
 */
@SpringBootApplication
public class SpringBootMain {
    //项目主入口
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMain.class, args);
    }
}
