package cn.iocoder.yudao.module.envir.dal.dataobject.river;

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
 * 河道 DO
 *
 * @author 芋道源码
 */
@TableName("river")
@KeySequence("river_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RiverDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 业务主键（UUID）
     */
    private String riverId;
    /**
     * 河道名称
     */
    private String name;
    /**
     * 责任河段（如：XX河上游0-5km/中游5-10km/下游10-15km）
     */
    private String responsibilitySection;
    /**
     * 所属区域（关联sys_area.area_code）
     */
    private String areaCode;
    /**
     * 水域保洁频次（可选值：每2小时/每日3次/每日2次/每日1次/每周2次/汛期加密）
     */
    private String waterCleaningFrequency;
    /**
     * 陆域清扫频次（可选值：每小时/每日2次/每日1次/隔日1次/每周1次）
     */
    private String landCleaningFrequency;
    /**
     * 水质监测周期（单位：天）
     */
    private Integer waterQualityCycle;
    /**
     * 水质监测详细数据（含透明度、异味等级、污染物浓度<氨氮/COD/总磷>等）
     */
    private String waterQualityData;
    /**
     * 污染溯源ID（关联溯源表）
     */
    private String pollutionSourceId;
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
     * 垃圾打捞量（单位：吨/日）
     */
    private BigDecimal wasteFishingVolume;
    /**
     * 保洁覆盖率（0.00-100.00）
     */
    private BigDecimal cleaningCoverage;
    /**
     * 水质达标率（0.00-100.00）
     */
    private BigDecimal waterQualityRate;
    /**
     * 垃圾打捞对比照片URL（多个用逗号分隔）
     */
    private String fishingPhotoUrl;
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