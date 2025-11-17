package cn.iocoder.yudao.module.datacenter.dal.dataobject.exchangepoint.industrytypicalexp;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 行业典型经验 DO
 *
 * @author 亘川智城
 */
@TableName("biz_industry_typical_exp")
@KeySequence("biz_industry_typical_exp_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IndustryTypicalExpDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 经验ID，唯一编码，UUID生成
     */
    private String industryTypicalExpId;
    /**
     * 经验标题，如“智慧城管事件处置效率提升经验”
     */
    private String expTitle;
    /**
     * 行业领域代码，取值如01=城管，02=环保，03=交通，行业领域字典表（sys_dict_industry_field）
     */
    private String industryFieldCode;
    /**
     * 行业领域名称，与行业领域代码同步，行业领域字典表（sys_dict_industry_field）
     */
    private String industryFieldName;
    /**
     * 案例单位，经验来源案例单位，如“XX市城管执法局”，部门信息表（sys_org）
     */
    private String caseOrg;
    /**
     * 经验核心亮点，支持富文本，如“1.引入AI分派：自动匹配处置部门；2.建立台账：跟踪处置进度”
     */
    private String expHighlights;
    /**
     * 经验实施效果，如“事件处置时长从24小时缩短至8小时，效率提升66%”
     */
    private String implementationEffect;
    /**
     * 附件ID，关联经验附件ID（如案例报告、效果对比图），文件存储表（sys_file_storage）
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
     * 点赞次数，用户点赞次数，默认0
     */
    private Integer likeCount;
    /**
     * 创建人，创建人ID，用户信息表（sys_user）
     */
    private String createUser;
    /**
     * 更新人，更新人ID，用户信息表（sys_user）
     */
    private String updateUser;
    /**
     * 分类扩展字段1，预留，如“经验类型：技术优化/流程改进”
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，如“适用规模：市级/区级”
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，如“实施周期：3个月”
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，如“最后点赞时间：2025-10-10”
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