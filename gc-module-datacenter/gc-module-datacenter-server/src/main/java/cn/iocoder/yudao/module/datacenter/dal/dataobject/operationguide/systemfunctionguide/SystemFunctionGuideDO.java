package cn.iocoder.yudao.module.datacenter.dal.dataobject.operationguide.systemfunctionguide;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 系统功能指南 DO
 *
 * @author 亘川智城
 */
@TableName("biz_system_function_guide")
@KeySequence("biz_system_function_guide_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SystemFunctionGuideDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 指南ID，唯一编码，UUID生成
     */
    private String systemFunctionGuideId;
    /**
     * 指南标题，如《地理编码坐标系配置指南》
     */
    private String guideTitle;
    /**
     * 关联功能ID，关联系统功能ID，系统功能表（sys_system_function）
     */
    private String relatedFunctionId;
    /**
     * 关联功能名称，与关联功能ID同步，系统功能表（sys_system_function）
     */
    private String relatedFunctionName;
    /**
     * 功能用途说明，如“配置坐标系，确保地理数据统一”
     */
    private String functionPurpose;
    /**
     * 操作步骤（支持富文本，含截图）
     */
    private String operSteps;
    /**
     * 常见问题及解决方案，如“坐标系配置失败：检查代码唯一性”
     */
    private String commonProblems;
    /**
     * 适用角色，如“管理员”，角色表（sys_role）
     */
    private String applicableRole;
    /**
     * 指南附件ID，关联附件ID（如操作视频），文件存储表（sys_file_storage）
     */
    private String guideAttachId;
    /**
     * 指南附件路径，附件存储路径，文件存储表（sys_file_storage）
     */
    private String guideAttachPath;
    /**
     * 指南状态：已发布/草稿，标识指南是否公开
     */
    private String guideStatus;
    /**
     * 创建人，创建人ID，用户信息表（sys_user）
     */
    private String createUser;
    /**
     * 更新人，更新人ID，用户信息表（sys_user）
     */
    private String updateUser;
    /**
     * 分类扩展字段1，预留，如“功能模块：地理编码/网格管理”
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，如“更新频率：季度/年度”
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，如“关联政策：GB/T 2260”
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，如“查看次数”
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