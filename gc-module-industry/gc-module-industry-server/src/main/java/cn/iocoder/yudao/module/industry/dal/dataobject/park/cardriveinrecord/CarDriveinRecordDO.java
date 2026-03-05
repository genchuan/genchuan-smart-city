package cn.iocoder.yudao.module.industry.dal.dataobject.park.cardriveinrecord;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 车辆入场记录 DO
 *
 * @author zhucongquan
 */
@TableName("park_car_drivein_record")
@KeySequence("park_car_drivein_record_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDriveinRecordDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 停车编号（车辆在停车场停车唯一编号）
     */
    private String recordId;
    /**
     * 入口编号
     */
    private String entranceNo;
    /**
     * 入口名称
     */
    private String entranceName;
    /**
     * 车牌类型
     */
    private String plateType;
    /**
     * 车牌号
     */
    private String plateNumber;
    /**
     * 进场时间
     */
    private LocalDateTime driveInTime;
    /**
     * 进场图片，图片URL地址或base64格式
     */
    private String driveInPhoto;
    /**
     * 空闲车位数
     */
    private Integer emptyPlot;
    /**
     * 收费员账号
     */
    private String operatorId;
    /**
     * 收费员名称
     */
    private String operatorName;
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

}