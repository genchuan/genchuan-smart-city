package cn.iocoder.yudao.module.park.dal.dataobject.park.resource.parkinglotinfo;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 停车场信息管理 DO
 *
 * @author zhucongquan
 */
@TableName("park_parking_lot_info")
@KeySequence("park_parking_lot_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingLotInfoDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 停车场ID
     */
    private String lotId;
    /**
     * 停车场名称
     */
    private String lotName;
    /**
     * 车场类型
     */
    private String lotType;
    /**
     * 所属区域
     */
    private String region;
    /**
     * 总车位数
     */
    private Integer totalSpaces;
    /**
     * 可用车位数
     */
    private Integer availableSpaces;
    /**
     * 车场状态
     */
    private String lotStatus;
    /**
     * 收费标准
     */
    private String feeStandard;
    /**
     * 营业时间
     */
    private String businessHours;
    /**
     * 运营商户
     */
    private String operator;
    /**
     * 联系人
     */
    private String contactPerson;
    /**
     * 联系电话
     */
    private String contactPhone;
    /**
     * 所属行政区划
     */
    private String areaCode;

}