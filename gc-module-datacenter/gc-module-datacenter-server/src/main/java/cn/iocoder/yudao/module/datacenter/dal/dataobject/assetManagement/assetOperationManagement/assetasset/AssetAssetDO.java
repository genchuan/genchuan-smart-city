package cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetOperationManagement.assetasset;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 资产关联资产 DO
 *
 * @author 亘川智城
 */
@TableName("rel_asset_asset")
@KeySequence("rel_asset_asset_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetAssetDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 关联ID
     */
    private String assetRelAssetId;
    /**
     * 从资产ID
     */
    private String slaveAssetId;
    /**
     * 从资产名称
     */
    private String slaveAssetName;
    /**
     * 主资产ID
     */
    private String masterAssetId;
    /**
     * 主资产名称
     */
    private String masterAssetName;
    /**
     * 关联类型
     */
    private String relType;
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