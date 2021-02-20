package com.itszt.manager.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.itszt.manager.entity.Member;
import com.itszt.manager.quartz.QuartzJob1;
import com.itszt.manager.util.ObjectMapperUtil;
import com.itszt.manager.util.RedisUtils;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.*;


@Configuration
public class QuartzConfig {
    /*需求一：quartz使用的测试demo*/

    @Autowired
    private RedisUtils redisUtils;

    @Bean
    public JobDetail jobDetail1(){
        return JobBuilder.newJob(QuartzJob1.class).storeDurably().build();
    }

    @Bean
    public Trigger trigger1(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(1) //每一秒执行一次
                .repeatForever(); //永久重复，一直执行下去
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail1())
                .withSchedule(scheduleBuilder)
                .build();
    }

    @Bean
    public JobDetail jobDetail2(){
        QuartzJobBean quartzJob2 = new QuartzJobBean() {
            @Override
            protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                System.out.println("内部类quartzJob2----" + sdf.format(new Date()));
            }
        };
        return JobBuilder.newJob(quartzJob2.getClass()).storeDurably().build();
    }

    @Bean
    public Trigger trigger2(){
        //JobDetail的bean注入不能省略
        //JobDetail jobDetail3 = JobBuilder.newJob(QuartzJob2.class).storeDurably().build();
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(2) //每2秒执行一次
                .repeatForever(); //永久重复，一直执行下去
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail2())
                .withSchedule(scheduleBuilder).build();
    }
    @Bean
    @QuartzDataSource
    @ConfigurationProperties(prefix = "spring.quartz.properties.org.quartz.datasource")
    DataSource quartzDataSource(){
        return DruidDataSourceBuilder.create().build();
    }


    /*需求:2：定时从redis中获取到所有的key,value值，放入map集合中返回并输出打印日志*/
    @Bean
    public JobDetail jobDetail3(){
        QuartzJobBean quartzJob3 = new QuartzJobBean() {
            @Override
            protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
                Set<String> allKeys = redisUtils.getAllKeys();
                HashMap<Object, Object> map = new HashMap<>();
                for (String key: allKeys) {
                    Object  value= redisUtils.get(key);
                    map.put(key,value);
                    if("memberList".equals(key)) break;
                }
                System.out.println("内部类quartzJob3----map:"+map);
            }
        };
        return JobBuilder.newJob(quartzJob3.getClass()).storeDurably().build();
    }

    @Bean
    public Trigger trigger3(){
        //JobDetail的bean注入不能省略
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(5) //每5秒执行一次
                .repeatForever(); //永久重复，一直执行下去
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail3())
                .withSchedule(scheduleBuilder).build();
    }



}
