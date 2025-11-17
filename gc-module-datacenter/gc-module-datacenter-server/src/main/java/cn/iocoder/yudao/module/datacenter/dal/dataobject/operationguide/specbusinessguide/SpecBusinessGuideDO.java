package cn.iocoder.yudao.module.datacenter.dal.dataobject.operationguide.specbusinessguide;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 专项业务指南 DO
 *
 * @author 亘川智城
 */
@TableName("biz_spec_business_guide")
@KeySequence("biz_spec_business_guide_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecBusinessGuideDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 指南ID，唯一编码，UUID生成
     */
    private String specBusinessGuideId;
    /**
     * 指南标题，如《城管事件上报处置指南》
     */
    private String guideTitle;
    /**
     * 业务领域代码，取值如01=城管，02=交通，03=环保，业务领域字典表（sys_dict_business_field）
     */
    private String businessFieldCode;
    /**
     * 业务领域名称，与业务领域代码同步，业务领域字典表（sys_dict_business_field）
     */
    private String businessFieldName;
    /**
     * 关联业务表名，关联专项业务表中文名称（如“城管事件表”），业务表字典表（sys_dict_business_table）
     */
    private String relatedTableName;
    /**
     * 关联业务表编码，关联专项业务表英文名称（如“biz_urban_evt”），业务表字典表（sys_dict_business_table）
     */
    private String relatedTableCode;
    /**
     * 业务流程说明，如“上报→分派→处置→结案”
     */
    private String businessProcessDesc;
    /**
     * 操作步骤（支持富文本，如“1.上报：录入事件类型、发生地点；2.分派：选择处置部门”）
     */
    private String operSteps;
    /**
     * 业务数据录入规范（如“事件类型需选择字典表枚举值，发生地点需含经纬度”）
     */
    private String dataSpec;
    /**
     * 适用角色，如“城管工作人员”，角色表（sys_role）
     */
    private String applicableRole;
    /**
     * 指南附件ID，关联附件ID（如操作视频、示例表格），文件存储表（sys_file_storage）
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
     * 分类扩展字段1，预留，如“业务优先级：高/中/低”
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，如“数据来源：人工上报/设备采集”
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，如“关联政策：GB/T 30428.2”
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，如“查看次数：120次”
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