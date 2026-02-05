package cn.iocoder.yudao.module.park.dal.dataobject.park.resource.inputcar;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 泊位录入车辆 DO
 *
 * @author zhucongquan
 */
@TableName("park_input_car")
@KeySequence("park_input_car_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkInputCarDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;

    /**
     * 车场ID
     */
    private String parkId;

    /**
     * 目标泊位号
     */
    private String targetBerthNo;
    /**
     * 车牌号码
     */
    private String carNumber;
    /**
     * 车辆类型
     */
    private String carType;
    /**
     * 车牌颜色
     */
    private String plateColor;
    /**
     * 停车状态
     */
    private String parkingStatus;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;
    /**
     * 通用扩展字段3
     */
    private String extCommon3;
    /**
     * 通用扩展字段4
     */
    private String extCommon4;

    /**
     * 入场时间
     */
    private LocalDateTime entryTime;

    /**
     * 出场时间
     */
    private LocalDateTime exitTime;

}