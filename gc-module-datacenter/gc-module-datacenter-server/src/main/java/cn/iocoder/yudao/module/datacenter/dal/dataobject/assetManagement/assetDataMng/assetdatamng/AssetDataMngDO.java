package cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetDataMng.assetdatamng;

import lombok.*;

import java.time.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 资产数据管理 DO
 *
 * @author 亘川智城
 */
@TableName("biz_asset_data_mng")
@KeySequence("biz_asset_data_mng_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetDataMngDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 资产数据ID
     */
    private String assetDataId;
    /**
     * 关联资产分类ID
     */
    private String relAssetCatId;
    /**
     * 关联资产分类名称
     */
    private String relAssetCatName;
    /**
     * 资产编码
     */
    private String assetCode;
    /**
     * 资产名称
     */
    private String assetName;
    /**
     * 资产状态
     */
    private String assetStatus;
    /**
     * 安装时间
     */
    private LocalDateTime installTime;
    /**
     * 使用年限
     */
    private Integer serviceLife;
    /**
     * 资产负责人
     */
    private String assetManager;
    /**
     * 资产负责人电话
     */
    private String assetManagerTel;
    /**
     * 资产描述
     */
    private String assetDesc;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 创建时间
     */
    private LocalDateTime createdTime;
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