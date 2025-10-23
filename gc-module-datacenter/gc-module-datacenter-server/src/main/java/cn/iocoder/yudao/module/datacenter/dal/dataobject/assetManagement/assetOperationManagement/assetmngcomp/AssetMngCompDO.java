package cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetOperationManagement.assetmngcomp;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 资产关联管理部件 DO
 *
 * @author 亘川智城
 */
@TableName("rel_asset_mng_comp")
@KeySequence("rel_asset_mng_comp_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetMngCompDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 关联ID
     */
    private String assetRelMngCompId;
    /**
     * 关联资产ID
     */
    private String relAssetId;
    /**
     * 关联资产名称
     */
    private String relAssetName;
    /**
     * 管理部件ID
     */
    private String mngCompId;
    /**
     * 管理部件编码
     */
    private String mngCompCode;
    /**
     * 管理部件名称
     */
    private String mngCompName;
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