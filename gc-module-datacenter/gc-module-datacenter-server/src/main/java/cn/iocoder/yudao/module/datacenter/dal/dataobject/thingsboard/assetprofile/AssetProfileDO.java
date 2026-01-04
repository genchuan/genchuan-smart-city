package cn.iocoder.yudao.module.datacenter.dal.dataobject.thingsboard.assetprofile;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 资产配置信息 DO
 *
 * @author zhucongquan
 */
@TableName("tb_asset_profile")
@KeySequence("tb_asset_profile_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetProfileDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 资产配置ID
     */
    private String profileId;
    /**
     * 实体类型
     */
    private String entityType;
    /**
     * 创建时间戳
     */
    private Long createdTime;
    /**
     * 租户ID
     */
    private String tenantIdTb;
    /**
     * 租户实体类型
     */
    private String tenantEntityType;
    /**
     * 资产配置名称
     */
    private String profileName;
    /**
     * 资产配置描述
     */
    private String profileDescription;
    /**
     * 资产配置图片
     */
    private String profileImage;
    /**
     * 默认规则链ID
     */
    private String defaultRuleChainId;
    /**
     * 默认仪表板ID
     */
    private String defaultDashboardId;
    /**
     * 默认队列名称
     */
    private String defaultQueueName;
    /**
     * 默认边缘规则链ID
     */
    private String defaultEdgeRuleChainId;
    /**
     * 外部ID
     */
    private String externalId;
    /**
     * 外部实体类型
     */
    private String externalEntityType;
    /**
     * 版本号
     */
    private Integer version;
    /**
     * 是否默认配置
     */
    private Boolean isDefault;

    // 系统字段
    private Long tenantId;           // 系统租户ID

    private LocalDateTime createTime;   // 创建时间

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