package cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetOperationManagement.assetgrid;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 资产关联网格 DO
 *
 * @author 亘川智城
 */
@TableName("rel_asset_grid")
@KeySequence("rel_asset_grid_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetGridDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 关联ID
     */
    private String assetRelGridId;
    /**
     * 关联资产ID
     */
    private String relAssetId;
    /**
     * 关联资产名称
     */
    private String relAssetName;
    /**
     * 网格编码
     */
    private String gridCode;
    /**
     * 网格名称
     */
    private String gridName;
    /**
     * 网格级别
     */
    private String gridLevel;
    /**
     * 关联时间
     */
    private LocalDateTime relTime;
    /**
     * 操作人
     */
    private String operUser;
    /**
     * 关联说明
     */
    private String relDesc;
    /**
     * 更新人
     */
    private String updateUser;
    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;
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