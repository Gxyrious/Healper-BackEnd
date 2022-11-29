package cn.edu.tongji.healper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author lc2002
 */
@EnableCaching  //开启springcache注解方式
@SpringBootApplication
public class HealperBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealperBackEndApplication.class, args);
    }

}
