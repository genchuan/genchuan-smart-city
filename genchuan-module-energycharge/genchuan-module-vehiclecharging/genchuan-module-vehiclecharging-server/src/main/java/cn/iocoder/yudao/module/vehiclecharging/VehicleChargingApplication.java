package cn.iocoder.yudao.module.vehiclecharging;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan({"cn.iocoder.yudao.module.vehiclecharging.dal.mysql"})
public class VehicleChargingApplication
{
    public static void main(String[] args) {
        SpringApplication.run(VehicleChargingApplication.class, args);
    }
}
