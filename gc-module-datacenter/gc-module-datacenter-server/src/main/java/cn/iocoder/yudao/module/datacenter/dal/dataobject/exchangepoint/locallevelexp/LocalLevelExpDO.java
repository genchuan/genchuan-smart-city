package cn.iocoder.yudao.module.datacenter.dal.dataobject.exchangepoint.locallevelexp;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 本级经验分享 DO
 *
 * @author 亘川智城
 */
@TableName("biz_local_level_exp")
@KeySequence("biz_local_level_exp_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocalLevelExpDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 经验ID，唯一编码，UUID生成
     */
    private String localLevelExpId;
    /**
     * 经验标题，如“XX市东城区网格划分经验”
     */
    private String expTitle;
    /**
     * 行政区划代码，本级行政区划代码，如330106=杭州市西湖区，行政区划表（sys_area）
     */
    private String regionCode;
    /**
     * 行政区划名称，与行政区划代码同步，行政区划表（sys_area）
     */
    private String regionName;
    /**
     * 关联部门代码，经验来源部门代码，部门信息表（sys_org）
     */
    private String deptCode;
    /**
     * 关联部门名称，与关联部门代码同步，部门信息表（sys_org）
     */
    private String deptName;
    /**
     * 经验详细内容，支持富文本，如“网格划分原则：按人口密度、地理边界划分”
     */
    private String expContent;
    /**
     * 经验实施步骤，如“1.调研区域人口；2.绘制地理边界；3.划分单元网格”
     */
    private String implementationSteps;
    /**
     * 实施成果，如“划分网格200个，事件处置效率提升30%”
     */
    private String achievementData;
    /**
     * 附件ID，关联经验附件ID（如划分方案、成果报表），文件存储表（sys_file_storage）
     */
    private String attachId;
    /**
     * 附件存储路径，文件存储表（sys_file_storage）
     */
    private String attachPath;
    /**
     * 发布状态：已发布/草稿，标识经验是否公开
     */
    private String publishStatus;
    /**
     * 分享次数，用户分享经验的次数，默认0
     */
    private Integer shareCount;
    /**
     * 创建人，创建人ID，用户信息表（sys_user）
     */
    private String createUser;
    /**
     * 更新人，更新人ID，用户信息表（sys_user）
     */
    private String updateUser;
    /**
     * 分类扩展字段1，预留，如“经验领域：网格管理/事件处置”
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，如“分享范围：本级/下级”
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，如“实施时间：2025年1月”
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，如“最后分享时间：2025-10-10”
     */
    private String extCommon2;
    /**
     * 系统创建时间
     */
    private LocalDateTime createTimeSys;
    /**
     * 系统更新时间
     */
    private LocalDateTime updateTimeSys;

}