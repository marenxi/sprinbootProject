package com.itszt.manager.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class SpringShiroConfig {
   /* @Bean
    public SecurityManager newSecurityManager(
            @Autowired Realm realm) {
        DefaultWebSecurityManager sManager=
                new DefaultWebSecurityManager();
        sManager.setRealm(realm);
        return sManager;
    }
    @Bean("shiroFilterFactory")
    public ShiroFilterFactoryBean newShiroFilterFactoryBean(
            @Autowired SecurityManager securityManager) {
        ShiroFilterFactoryBean sfBean=
                new ShiroFilterFactoryBean();
        sfBean.setSecurityManager(securityManager);
        //假如没有认证请求先访问此认证的url
        sfBean.setLoginUrl("/login");
        //定义map指定请求过滤规则(哪些资源允许匿名访问,哪些必须认证访问)
        LinkedHashMap<String,String> map=
                new LinkedHashMap<>();
        //静态资源允许匿名访问:"anon"
        map.put("/js/**","anon");
        map.put("/layui/**","anon");
        map.put("/user/**","anon");
        map.put("/login","anon");
        map.put("/doLogout", "logout");//自动查LoginUrl
        //除了匿名访问的资源,其它都要认证("authc")后访问
        map.put("/**","authc");
        sfBean.setFilterChainDefinitionMap(map);
        return sfBean;
    }
    //1.配置shiro中bean对象的生命周期管理
    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor
    newLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
*/

}
