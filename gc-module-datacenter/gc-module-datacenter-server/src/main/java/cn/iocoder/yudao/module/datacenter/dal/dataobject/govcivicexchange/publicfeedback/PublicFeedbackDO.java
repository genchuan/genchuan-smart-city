package cn.iocoder.yudao.module.datacenter.dal.dataobject.govcivicexchange.publicfeedback;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 意见反馈 DO
 *
 * @author 亘川智城
 */
@TableName("biz_public_feedback")
@KeySequence("biz_public_feedback_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicFeedbackDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 反馈ID，唯一编码，UUID生成
     */
    private String feedbackId;
    /**
     * 反馈单号，格式“FK+YYYYMMDD+6位流水号”
     */
    private String feedbackNo;
    /**
     * 反馈类型编码，类型（如“功能问题”“优化建议”），字典表（sys_dict）
     */
    private String feedbackTypeCode;
    /**
     * 反馈类型名称，与类型编码同步，字典表（sys_dict）
     */
    private String feedbackTypeName;
    /**
     * 反馈内容，市民反馈的问题或建议
     */
    private String feedbackContent;
    /**
     * 上传图片路径，问题截图，多个用逗号分隔
     */
    private String imgPath;
    /**
     * 反馈人ID，登录用户ID（未登录为空），用户表（sys_user）
     */
    private Long feedbackUserId;
    /**
     * 反馈人姓名
     */
    private String feedbackUserName;
    /**
     * 反馈人电话，加密存储（便于回访）
     */
    private String feedbackUserPhone;
    /**
     * 评估状态：1=待评估，2=已采纳，3=不采纳，评估结果
     */
    private Integer evalStatus;
    /**
     * 评估意见，管理员评估说明，如“采纳，下版本优化”
     */
    private String evalOpinion;
    /**
     * 评估人ID，评估管理员ID，用户表（sys_user）
     */
    private Long evalUserId;
    /**
     * 评估时间
     */
    private LocalDateTime evalTime;
    /**
     * 反馈时间，市民提交时间
     */
    private LocalDateTime feedbackTime;
    /**
     * 备注，补充说明，如“已联系用户回访”
     */
    private String remark;
    /**
     * 分类扩展字段1，预留，标识反馈模块，如投诉/咨询
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，标识紧急程度，如普通/紧急
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，存储优化版本号
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，存储回访结果
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