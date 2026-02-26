package cn.iocoder.yudao.module.envir.dal.dataobject.commercialstreet;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 商业街 DO
 *
 * @author 芋道源码
 */
@TableName("commercial_street")
@KeySequence("commercial_street_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommercialStreetDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 业务主键（UUID）
     */
    private String commercialStreetId;
    /**
     * 商业街名称
     */
    private String name;
    /**
     * 商业街地址
     */
    private String address;
    /**
     * 所属区域（关联sys_area.area_code）
     */
    private String areaCode;
    /**
     * 保洁频次（可选值：每小时/每日/每周/定点保洁/实时保洁）
     */
    private String cleaningFrequency;
    /**
     * 巡回保洁间隔（单位：小时）
     */
    private Integer patrolInterval;
    /**
     * 垃圾收集点位数量
     */
    private Integer collectionPoints;
    /**
     * 垃圾清运间隔（单位：小时）
     */
    private Integer transferInterval;
    /**
     * 设施类型（关联sys_facility.sys_facility_id，多个用逗号分隔）
     */
    private String facilityIds;
    /**
     * 负责人（关联sys_user.id）
     */
    private String managerId;
    /**
     * 业务创建人（关联sys_user.id）
     */
    private String abnormalCreateBy;
    /**
     * 业务创建时间
     */
    private LocalDateTime abnormalCreateTime;
    /**
     * 业务更新时间
     */
    private LocalDateTime abnormalUpdateTime;
    /**
     * 保洁覆盖率（0.00-100.00）
     */
    private BigDecimal cleaningCoverage;
    /**
     * 设施完好率（0.00-100.00）
     */
    private BigDecimal facilityRate;
    /**
     * 问题平均处置时长（单位：分钟）
     */
    private BigDecimal disposalDuration;
    /**
     * 问题处置对比照片URL（多个用逗号分隔）
     */
    private String problemPhotoUrl;
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