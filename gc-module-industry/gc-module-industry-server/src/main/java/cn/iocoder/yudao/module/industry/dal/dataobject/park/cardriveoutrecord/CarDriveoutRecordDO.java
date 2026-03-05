package cn.iocoder.yudao.module.industry.dal.dataobject.park.cardriveoutrecord;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 车辆出场记录 DO
 *
 * @author zhucongquan
 */
@TableName("park_car_driveout_record")
@KeySequence("park_car_driveout_record_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDriveoutRecordDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 停车编号（车辆在停车场唯一编号）
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
     * 车牌类型，参考附录
     */
    private String plateType;
    /**
     * 车牌号
     */
    private String plateNumber;
    /**
     * 进场时间，格式yyyy-MM-dd HH:mm:ss
     */
    private LocalDateTime driveInTime;
    /**
     * 进场图片，图片URL地址或base64格式
     */
    private String driveInPhoto;
    /**
     * 出口编号
     */
    private String exitNo;
    /**
     * 出口名称
     */
    private String exitName;
    /**
     * 出场时间，格式yyyy-MM-dd HH:mm:ss
     */
    private LocalDateTime driveOutTime;
    /**
     * 出场图片，图片URL地址或base64格式
     */
    private String driveOutPhoto;
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
     * 应付金额，单位分
     */
    private BigDecimal shouldPay;
    /**
     * 实付金额，单位分
     */
    private BigDecimal actualPay;
    /**
     * 出场类型，参考附录
     */
    private String outType;
    /**
     * 免费原因或异常原因
     */
    private String outRemark;
    /**
     * 支付方式，参考附录
     */
    private String payMethod;
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