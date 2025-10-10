package cn.iocoder.yudao.module.datacenter.dal.dataobject.businessguidance.policyinterpretation;

import lombok.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 政策解读信息 DO
 *
 * @author Gyh
 */
@TableName("gc_policy_interpretation_info")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PolicyInterpretationInfoDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;

    /**
     * 解读ID，唯一编码，采用UUID
     */
    private String interpretationId;

    /**
     * 解读标题
     */
    private String interpretationTitle;

    /**
     * 关联政策类型，国家政策/地方法规
     */
    private String relatedPolicyType;

    /**
     * 关联国家政策ID或地方法规ID
     */
    private String relatedPolicyId;

    /**
     * 关联政策名称
     */
    private String relatedPolicyName;

    /**
     * 富文本内容，含背景、条款解读、实施要求
     */
    private String interpretationContent;

    /**
     * 解读类型，官方解读/专家解读/实务解读
     */
    private String interpretationType;

    /**
     * 发布解读的管理员账号
     */
    private String issueUserId;

    /**
     * 发布人姓名
     */
    private String issueUserName;

    /**
     * 发布时间
     */
    private LocalDateTime issueTime;

    /**
     * 解读状态，已发布/已下架
     */
    private String interpretationStatus;

    /**
     * 下架原因
     */
    private String offShelfReason;

    /**
     * 修改解读的管理员账号
     */
    private String updateUserId;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 扩展分类字段
     */
    private String extendCategory;
}