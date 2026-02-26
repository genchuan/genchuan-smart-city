package cn.iocoder.yudao.module.envir.dal.dataobject.park;

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
 * 公园 DO
 *
 * @author 芋道源码
 */
@TableName("park")
@KeySequence("park_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 业务主键（UUID）
     */
    private String parkId;
    /**
     * 公园名称
     */
    private String name;
    /**
     * 公园地址
     */
    private String address;
    /**
     * 所属区域（关联sys_area.area_code）
     */
    private String areaCode;
    /**
     * 保洁频次（可选值：每小时/每日/每周/定点保洁/节假日加密）
     */
    private String cleaningFrequency;
    /**
     * 绿化品类字典表ID（多个用逗号分隔）
     */
    private String greenTypeIds;
    /**
     * 绿化养护周期（单位：天）
     */
    private Integer greenMaintenanceCycle;
    /**
     * 设施检查周期（单位：天）
     */
    private Integer facilityCheckCycle;
    /**
     * 垃圾清运频次（可选值：每2小时/每日3次/每日2次/每日1次）
     */
    private String wasteTransferFrequency;
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
     * 保洁达标率（0.00-100.00）
     */
    private BigDecimal cleaningRate;
    /**
     * 绿化存活率（0.00-100.00）
     */
    private BigDecimal greenSurvivalRate;
    /**
     * 设施完好率（0.00-100.00）
     */
    private BigDecimal facilityRate;
    /**
     * 绿化养护对比照片URL（多个用逗号分隔）
     */
    private String greenPhotoUrl;
    /**
     * 设施维护对比照片URL（多个用逗号分隔）
     */
    private String facilityPhotoUrl;
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