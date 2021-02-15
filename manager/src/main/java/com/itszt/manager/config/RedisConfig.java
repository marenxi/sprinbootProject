package com.itszt.manager.config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 完成对Redis的整合的一些配置
 *
 *
 */
@Configuration
public class RedisConfig {

    /**
     * 1.创建JedisPoolConfig对象。在该对象中完成一些链接池配置
     * @ConfigurationProperties:会将前缀相同的内容创建一个实体。
     */
    @Bean
    @ConfigurationProperties(prefix="spring.redis.pool")	//关联application.properties配置文件
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig config = new JedisPoolConfig();
        //配置连接池对象(可在此配置,但推荐在application.properties中配置)
		/*//最大空闲数,默认值8
		config.setMaxIdle(10);
		//最小空闲数,默认值0
		config.setMinIdle(5);
		//最大链接数,默认值8
		config.setMaxTotal(20);*/
        return config;
    }

    /**
     * 2.创建JedisConnectionFactory：配置redis链接信息
     */
    @Bean
    @ConfigurationProperties(prefix="spring.redis")	//关联application.properties配置文件
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig config){
        JedisConnectionFactory factory = new JedisConnectionFactory();
        //关联链接池的配置对象
        factory.setPoolConfig(config);
        //配置链接Redis的信息(可在此配置,但推荐在application.properties中配置)
        //主机地址
		/*factory.setHostName("192.168.70.128");
		//端口
		factory.setPort(6379);*/
        return factory;
    }

    /**
     * 3.创建RedisTemplate:用于执行Redis操作的方法
     * (最重要的方法,之前的方法全是为了获得最后的RedisTemplates对象)
     */
    @Bean
    public RedisTemplate<String,Object> redisTemplate(JedisConnectionFactory factory){
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        //关联
        template.setConnectionFactory(factory);

        //为key设置序列化器
        template.setKeySerializer(new StringRedisSerializer());
        //为value设置序列化器
        template.setValueSerializer(new StringRedisSerializer());

        return template;
    }

}


