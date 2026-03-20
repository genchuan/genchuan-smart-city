package cn.iocoder.yudao.module.appearance.dal.dataobject.outdoorad;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 户外广告 DO
 *
 * @author 亘川智城
 */
@TableName("appearance.outdoor_ad")
@KeySequence("outdoor_ad_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OutdoorAdDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 广告ID
     */
    private String outdoorAdId;
    /**
     * 广告名称
     */
    private String name;
    /**
     * 广告位置
     */
    private String location;
    /**
     * 审批尺寸
     */
    private String approvedSize;
    /**
     * 实际尺寸
     */
    private String actualSize;
    /**
     * 倾斜角度
     */
    private BigDecimal tiltAngle;
    /**
     * 关联sys_damage_status.id，破损状态
     */
    private String damageStatusId;
    /**
     * 关联sys_ad_status.id，广告状态
     */
    private String adStatusId;
    /**
     * 关联sys_area.area_code，所属区域
     */
    private String areaCode;
    /**
     * 关联sys_user.id，监管员
     */
    private String supervisorId;
    /**
     * 关联sys_warning_type.id，预警类型
     */
    private String warningTypeId;
    /**
     * 预警时间
     */
    private LocalDateTime warningTime;
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

    // 关联表字段
    /**
     * 破损状态名称
     */
    @TableField(exist = false)
    private String damageStatusName;

    /**
     * 广告状态名称
     */
    @TableField(exist = false)
    private String adStatusName;

    /**
     * 区域名称
     */
    @TableField(exist = false)
    private String areaName;

    /**
     * 监管员名称
     */
    @TableField(exist = false)
    private String supervisorName;

    /**
     * 预警类型名称
     */
    @TableField(exist = false)
    private String warningTypeName;

    /**
     * 工单编号
     */
    @TableField(exist = false)
    private String orderNo;

    /**
     * 工单状态名称
     */
    @TableField(exist = false)
    private String orderStatusName;

    /**
     * 处置人名称
     */
    @TableField(exist = false)
    private String dealByName;

    /**
     * 复核结果名称
     */
    @TableField(exist = false)
    private String reviewResultName;

}