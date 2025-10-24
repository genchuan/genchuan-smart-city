package cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetDataMng.assetdataqualck;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 资产数据质量检查 DO
 *
 * @author 亘川智城
 */
@TableName("biz_asset_data_qual_ck")
@KeySequence("biz_asset_data_qual_ck_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetDataQualCkDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 质量检查ID
     */
    private String assetDataQualId;
    /**
     * 关联资产ID
     */
    private String relAssetId;
    /**
     * 关联资产名称
     */
    private String relAssetName;
    /**
     * 检查数据类型
     */
    private String ckDataType;
    /**
     * 检查项名称
     */
    private String ckItemName;
    /**
     * 检查项代码
     */
    private String ckItemCode;
    /**
     * 检查结果
     */
    private String ckResult;
    /**
     * 错误描述
     */
    private String errorDesc;
    /**
     * 检查时间
     */
    private LocalDateTime ckTime;
    /**
     * 检查方式
     */
    private String ckMethod;
    /**
     * 处理状态
     */
    private String handleStatus;
    /**
     * 处理人
     */
    private String handleUser;
    /**
     * 处理时间
     */
    private LocalDateTime handleTime;
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