package cn.iocoder.yudao.module.datacenter.dal.dataobject.mngcompcfg.bizmngcompspatial;

import lombok.*;

import java.math.BigDecimal;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 管理部件空间数据 DO
 *
 * @author 亘川智城
 */
@TableName("gc_biz_mng_comp_spatial")
@KeySequence("gc_biz_mng_comp_spatial_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BizMngCompSpatialDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 空间数据ID，唯一编码，UUID生成
     */
    private String mngCompSpatialId;
    /**
     * 关联管理部件ID，一对一，关联管理部件表(gc_biz_mng_comp)
     */
    private String mngCompId;
    /**
     * 关联部件名称，与部件ID同步，不可改，关联管理部件表(gc_biz_mng_comp)
     */
    private String compName;
    /**
     * 坐标系类型，固定，符合国标，2000国家大地坐标系
     */
    private String coordSystem;
    /**
     * 坐标X，经度，保留2位小数，范围 - 180.00至180.00
     */
    private BigDecimal coordX;
    /**
     * 坐标Y，纬度，保留2位小数，范围 - 90.00至90.00
     */
    private BigDecimal coordY;
    /**
     * 高程，可选，米，1985国家高程基准，保留3位小数
     */
    private BigDecimal elevation;
    /**
     * 定位精度，米，±0.5/±1.0/±10.0，定位精度等级对应的误差
     */
    private BigDecimal accuracy;
    /**
     * 定位精度等级，A/B/C，A类±0.5m、B类±1.0m、C类±10.0m
     */
    private String accuracyLevel;
    /**
     * 测绘单位，测绘实施单位
     */
    private String surveyUnit;
    /**
     * 系统创建时间
     */
    private LocalDateTime createTimeSys;
    /**
     * 系统更新时间
     */
    private LocalDateTime updateTimeSys;

}