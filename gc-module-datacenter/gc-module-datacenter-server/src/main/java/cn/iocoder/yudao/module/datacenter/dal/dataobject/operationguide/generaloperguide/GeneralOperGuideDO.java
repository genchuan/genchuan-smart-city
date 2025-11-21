package cn.iocoder.yudao.module.datacenter.dal.dataobject.operationguide.generaloperguide;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import java.time.LocalDate;

/**
 * 通用操作指南 DO
 *
 * @author 亘川智城
 */
@TableName("biz_general_oper_guide")
@KeySequence("biz_general_oper_guide_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralOperGuideDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 指南ID，唯一编码，UUID生成
     */
    private String generalOperGuideId;
    /**
     * 指南标题，如《系统登录与权限切换指南》
     */
    private String guideTitle;
    /**
     * 操作场景，取值如登录/权限/数据操作，操作场景字典表（sys_dict_oper_scenario）
     */
    private String operScenario;
    /**
     * 操作步骤（支持富文本，含截图插入）
     */
    private String operSteps;
    /**
     * 适用角色，如“所有用户”“管理员”，角色表（sys_role）
     */
    private String applicableRole;
    /**
     * 版本号，如“V1.0”
     */
    private String version;
    /**
     * 生效时间，格式：YYYYMMDD
     */
    private LocalDate effectiveTime;
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
     * 分类扩展字段1，预留，如“指南类型：文字/视频”
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，如“难度等级：简单/中等”
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，如“关联功能：数据导入”
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