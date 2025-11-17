package cn.iocoder.yudao.module.datacenter.dal.dataobject.operationguide.faq;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 常见问题解答 DO
 *
 * @author 亘川智城
 */
@TableName("biz_faq")
@KeySequence("biz_faq_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FaqDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * FAQ ID，唯一编码，UUID生成
     */
    private String faqId;
    /**
     * 问题类型代码，01=系统操作问题，02=业务数据问题，03=流程执行问题，问题类型字典表（sys_dict_question_type）
     */
    private String questionTypeCode;
    /**
     * 问题类型名称，与问题类型代码同步，问题类型字典表（sys_dict_question_type）
     */
    private String questionTypeName;
    /**
     * 问题描述，常见问题描述，如“地理编码坐标系配置失败怎么办？”
     */
    private String questionDesc;
    /**
     * 解答方案，问题解决方案，支持富文本，如“1.检查坐标系代码是否重复；2.确认坐标系名称含‘坐标系’关键词”
     */
    private String answerSolution;
    /**
     * 关联指南ID，关联操作指南/业务指南ID，如通用操作指南ID、专项业务指南ID，通用操作指南表（biz_general_oper_guide）/专项业务指南表（biz_spec_business_guide）
     */
    private String relatedGuideId;
    /**
     * 关联指南名称，与关联指南ID同步，通用操作指南表（biz_general_oper_guide）/专项业务指南表（biz_spec_business_guide）
     */
    private String relatedGuideName;
    /**
     * 排序序号，1 - 999，FAQ列表展示排序优先级，数值越小越靠前
     */
    private Integer sortNum;
    /**
     * 是否置顶，0 = 否，1 = 是，标识FAQ是否置顶展示（仅10条可置顶）
     */
    private String isTop;
    /**
     * 查看次数，用户查看FAQ的次数，默认0
     */
    private Integer viewCount;
    /**
     * 创建人，创建人ID，用户信息表（sys_user）
     */
    private String createUser;
    /**
     * 更新人，更新人ID，用户信息表（sys_user）
     */
    private String updateUser;
    /**
     * 分类扩展字段1，预留，如“问题难度：简单/中等/复杂”
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，如“关联模块：地理编码/网格管理”
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，如“解决率：98%”
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，如“最后查看时间：2025 - 10 - 10”
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