package cn.iocoder.yudao.module.envir;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目的启动类
 *
 * @author 芋道源码
 */
@SpringBootApplication
@MapperScan("cn.iocoder.yudao.module.envir.dal.mysql.**")
public class EnvirServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnvirServerApplication.class, args);
    }
}