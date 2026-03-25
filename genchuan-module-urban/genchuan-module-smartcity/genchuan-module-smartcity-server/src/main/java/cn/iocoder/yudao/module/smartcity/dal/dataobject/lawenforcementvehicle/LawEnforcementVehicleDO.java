package cn.iocoder.yudao.module.smartcity.dal.dataobject.lawenforcementvehicle;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 执法车辆管理 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_law_enforcement_vehicle")
@KeySequence("smartcity_law_enforcement_vehicle_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LawEnforcementVehicleDO extends BaseDO {

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
     * 车辆颜色
     */
    private String vehicleColor;
    /**
     * 车架号
     */
    private String vin;
    /**
     * 发动机号
     */
    private String engineNo;
    /**
     * 购置时间
     */
    private String purchaseTime;
    /**
     * 登记注册日期
     */
    private LocalDateTime registrationDate;
    /**
     * 所属执法部门
     */
    private String belongingDepartment;
    /**
     * 使用性质
     */
    private String natureOfUse;
    /**
     * 车辆用途
     */
    private String vehicleUsage;
    /**
     * 车辆状态
     */
    private String vehicleStatus;
    /**
     * 年检到期日期
     */
    private LocalDateTime annualInspectionDate;
    /**
     * 保险截止日期
     */
    private LocalDateTime insuranceDeadline;
    /**
     * 保险类型
     */
    private String typesOfInsurance;
    /**
     * 驾驶员姓名
     */
    private String driverName;
    /**
     * 驾驶员联系方式
     */
    private String driverContactInformation;
    /**
     * 行驶里程
     */
    private String mileage;
    /**
     * 维修记录
     */
    private String maintenanceRecord;
    /**
     * 保养记录
     */
    private String maintenanceRecords;
    /**
     * 加油记录
     */
    private String refuelingRecord;
    /**
     * 违章记录
     */
    private String violationRecords;

}