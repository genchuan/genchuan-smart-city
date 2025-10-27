package cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetStatReport.assetcatrpt;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 资产分类统计 DO
 *
 * @author 亘川智城
 */
@TableName("stat_asset_cat_rpt")
@KeySequence("stat_asset_cat_rpt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetCatRptDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 统计ID
     */
    private String assetCatStatId;
    /**
     * 统计周期
     */
    private String statCycle;
    /**
     * 统计周期名称
     */
    private String statCycleName;
    /**
     * 行政区划代码
     */
    private String regionCode;
    /**
     * 行政区划名称
     */
    private String regionName;
    /**
     * 资产大类ID
     */
    private String assetMajorId;
    /**
     * 资产大类名称
     */
    private String assetMajorName;
    /**
     * 资产中类ID
     */
    private String assetMidId;
    /**
     * 资产中类名称
     */
    private String assetMidName;
    /**
     * 资产小类ID
     */
    private String assetMinorId;
    /**
     * 资产小类名称
     */
    private String assetMinorName;
    /**
     * 资产总数
     */
    private Integer totalAssetCount;
    /**
     * 启用资产数
     */
    private Integer enabledAssetCount;
    /**
     * 禁用资产数
     */
    private Integer disabledAssetCount;
    /**
     * 新增资产数
     */
    private Integer newAssetCount;
    /**
     * 更新资产数
     */
    private Integer updateAssetCount;
    /**
     * 统计时间
     */
    private LocalDateTime statTime;
    /**
     * 统计人
     */
    private String statUser;
    /**
     * 报表备注
     */
    private String rptRemark;
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