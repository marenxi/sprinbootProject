package com.itszt.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description:
 * @author: marenxi@jiuxian.com
 * @date: 2019-05-05 14:10/星期日
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan("com.itszt")
public class SpringBootMain {
    //项目主入口
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMain.class, args);
    }
}
