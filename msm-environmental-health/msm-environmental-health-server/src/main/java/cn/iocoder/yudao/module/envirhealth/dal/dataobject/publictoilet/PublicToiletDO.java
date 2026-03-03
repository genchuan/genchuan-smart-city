package cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 公厕 DO
 *
 * @author 芋道源码
 */
@TableName("public_toilet")
@KeySequence("public_toilet_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicToiletDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 公厕主键（UUID）
     */
    private String toiletId;
    /**
     * 公厕名称
     */
    private String name;
    /**
     * 公厕位置
     */
    private String location;
    /**
     * 关联sys_area.area_code
     */
    private String areaCode;
    /**
     * 开放时段
     */
    private String openHours;
    /**
     * 蹲位数量
     */
    private Integer stallCount;
    /**
     * 关联sys_operation_status.id
     */
    private String operationStatusId;
    /**
     * 关联sys_user.id
     */
    private String managerId;
    /**
     * 保洁达标率
     */
    private BigDecimal cleaningRate;
    /**
     * 投诉办结率
     */
    private BigDecimal complaintRate;
    /**
     * 耗材库存预警数
     */
    private Integer warningCount;
    /**
     * 设施完好率
     */
    private BigDecimal facilityRate;
    /**
     * 保洁频次
     */
    private String cleaningFrequency;
    /**
     * 保洁时段
     */
    private String cleaningTime;
    /**
     * 保洁内容
     */
    private String cleaningContent;
    /**
     * 保洁标准
     */
    private String cleaningStandard;
    /**
     * 保洁人员IDs，JSON
     */
    private String cleanerIds;
    /**
     * 耗材库存
     */
    private String consumableStock;
    /**
     * 预警阈值
     */
    private Integer consumableThreshold;
    /**
     * 缺口数量
     */
    private Integer consumableGap;
    /**
     * 上次补充时间
     */
    private LocalDateTime lastSupplyTime;
    /**
     * 补充周期
     */
    private String supplyCycle;
    /**
     * 通用扩展字段1
     */
    @JsonIgnore
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    @JsonIgnore
    private String extCommon2;
    /**
     * 通用扩展字段3
     */
    @JsonIgnore
    private String extCommon3;
    /**
     * 通用扩展字段4
     */
    @JsonIgnore
    private String extCommon4;

}