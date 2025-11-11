package cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.data.basicmonpt;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 基础监测点位 DO
 *
 * @author zcq
 */
@TableName("stat_basic_mon_pt")
@KeySequence("stat_basic_mon_pt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasicMonPtDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 点位ID
     */
    private String ptId;
    /**
     * 点位名称
     */
    private String ptName;
    /**
     * 点位编码
     */
    private String ptCode;
    /**
     * 点位类型
     */
    private String ptType;
    /**
     * 所属区域代码
     */
    private String areaCode;
    /**
     * 所属区域名称
     */
    private String areaName;
    /**
     * 关联设备编码
     */
    private String deviceCode;
    /**
     * 关联设备名称
     */
    private String deviceName;
    /**
     * 点位坐标X(度)
     */
    private BigDecimal ptCoordX;
    /**
     * 点位坐标Y(度)
     */
    private BigDecimal ptCoordY;
    /**
     * 负责人ID
     */
    private Long mngrId;
    /**
     * 负责人姓名
     */
    private String mngrName;
    /**
     * 负责人联系方式
     */
    private String mngrPhone;
    /**
     * 点位状态
     */
    private Boolean ptStatus;
    /**
     * 创建时间
     */
    private LocalDateTime createTimeSys;
    /**
     * 更新时间
     */
    private LocalDateTime updateTimeSys;
    /**
     * 分类扩展字段1
     */
    private String extCat1;
    /**
     * 分类扩展字段2
     */
    private String extCat2;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;

}