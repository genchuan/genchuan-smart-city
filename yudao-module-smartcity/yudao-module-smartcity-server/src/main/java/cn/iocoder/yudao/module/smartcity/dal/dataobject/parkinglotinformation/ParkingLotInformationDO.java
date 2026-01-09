package cn.iocoder.yudao.module.smartcity.dal.dataobject.parkinglotinformation;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 停车场信息管理 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_parking_lot_information")
@KeySequence("smartcity_parking_lot_information_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingLotInformationDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 位置
     */
    private String position;
    /**
     * 联系电话
     */
    private String contactNumber;
    /**
     * 运营时间
     */
    private LocalDateTime operationTime;
    /**
     * 总车位数
     */
    private String totalNumber;
    /**
     * 收费标准
     */
    private String feeStandards;
    /**
     * 计费方式
     */
    private String billingMethod;
    /**
     * 车位编号
     */
    private String parkingSpaceNumber;
    /**
     * 停车场地图
     */
    private String parkingLotMap;
    /**
     * 监控设备数量
     */
    private String numberDevices;
    /**
     * 出入口数量
     */
    private String numberOfEntrancesExits;

}