package cn.iocoder.yudao.module.smartcity.dal.dataobject.vehicleinformation;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 车辆信息 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_vehicle_information")
@KeySequence("smartcity_vehicle_information_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleInformationDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 车牌号
     */
    private String licensePlateNumber;
    /**
     * 车辆品牌
     */
    private String vehicleBrand;
    /**
     * 车辆型号
     */
    private String model;
    /**
     * 车辆类型
     */
    private String vehicleType;
    /**
     * 车架号
     */
    private String vin;
    /**
     * 发动机号
     */
    private String engineNo;
    /**
     * 注册日期
     */
    private LocalDateTime registrationDate;
    /**
     * 登记车主姓名
     */
    private String registerTheName;
    /**
     * 车主身份证号
     */
    private String ownerIdNumberNumber;
    /**
     * 车主联系电话
     */
    private String carOwnerPhoneNumber;
    /**
     * 车辆颜色
     */
    private String vehicleColor;
    /**
     * 座位数
     */
    private String seats;
    /**
     * 出厂日期
     */
    private String dateOfProduction;
    /**
     * 购置价格
     */
    private String purchasePrice;
    /**
     * 使用性质
     */
    private String natureOfUse;
    /**
     * 年检有效期
     */
    private String annualInspectionPeriod;
    /**
     * 保险有效期
     */
    private String periodOfInsurance;
    /**
     * 保险类型
     */
    private String typesOfInsurance;
    /**
     * 环保排放标准
     */
    private String environmentalStandards;
    /**
     * 车辆用途
     */
    private String vehicleUsage;

}