package cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetOperationManagement.assetcatmng;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 资产分类管理 DO
 *
 * @author 亘川智城
 */
@TableName("biz_asset_cat_mng")
@KeySequence("biz_asset_cat_mng_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetCatMngDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 资产分类ID
     */
    private String assetCatId;
    /**
     * 关联分类规则ID
     */
    private String relCatRuleId;
    /**
     * 资产分类编码
     */
    private String assetCatCode;
    /**
     * 资产分类名称
     */
    private String assetCatName;
    /**
     * 分类层级
     */
    private String catLevel;
    /**
     * 上级分类ID
     */
    private String parentCatId;
    /**
     * 上级分类名称
     */
    private String parentCatName;
    /**
     * 分类说明
     */
    private String catDesc;
    /**
     * 启用状态
     */
    private String enableStatus;
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