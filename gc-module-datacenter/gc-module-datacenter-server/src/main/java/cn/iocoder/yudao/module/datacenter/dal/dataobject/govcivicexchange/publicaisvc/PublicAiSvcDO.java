package cn.iocoder.yudao.module.datacenter.dal.dataobject.govcivicexchange.publicaisvc;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 智能客服知识库 DO
 *
 * @author 亘川智城
 */
@TableName("biz_public_ai_svc")
@KeySequence("biz_public_ai_svc_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicAiSvcDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 知识库ID，唯一编码，UUID生成
     */
    private String aiSvcId;
    /**
     * 问题类型编码，问题分类，如“社保”“公积金”，字典表（sys_dict）
     */
    private String questionTypeCode;
    /**
     * 问题类型名称，与类型编码同步，字典表（sys_dict）
     */
    private String questionTypeName;
    /**
     * 问题关键词，多个关键词用逗号分隔，如“个税查询,个税缴纳记录”
     */
    private String questionKeyword;
    /**
     * 标准问题，规范化问题表述，如“如何查询个税缴纳记录”
     */
    private String stdQuestion;
    /**
     * 标准答案，分点回复，支持富文本
     */
    private String stdAnswer;
    /**
     * 附件路径，辅助说明附件，如操作截图
     */
    private String attachPath;
    /**
     * 使用次数，智能回复时累计次数，默认0
     */
    private Integer useCount;
    /**
     * 更新人ID，更新人ID，用户表（sys_user）
     */
    private Long updateUserId;
    /**
     * 更新人姓名，与更新人ID同步，用户表（sys_user）
     */
    private String updateUserName;
    /**
     * 启用状态：1=启用，0=禁用，标识是否参与智能匹配
     */
    private Integer enableStatus;
    /**
     * 备注，补充说明，如“高频问题，优先匹配”
     */
    private String remark;
    /**
     * 分类扩展字段1，预留，标识问题难度，如简单/复杂
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，标识回复类型，如文本/图文
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，存储人工客服转办阈值
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，存储关键词匹配阈值
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