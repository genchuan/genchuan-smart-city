package cn.iocoder.yudao.module.vehiclepass;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目的启动类
 *
 * @author 芋道源码
 */
@SpringBootApplication
@MapperScan("cn.iocoder.yudao.module.vehiclepass.dal.mysql")
public class VehiclepassServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(VehiclepassServerApplication.class, args);
    }

}



