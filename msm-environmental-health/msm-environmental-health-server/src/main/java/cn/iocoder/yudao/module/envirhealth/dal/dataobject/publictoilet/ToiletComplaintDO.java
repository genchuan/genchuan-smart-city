package cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 公厕投诉 DO
 *
 * @author 芋道源码
 */
@TableName("public_toilet_complaint")
@KeySequence("public_toilet_complaint_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ToiletComplaintDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 投诉主键（UUID）
     */
    private String complaintId;
    /**
     * 关联public_toilet.toilet_id
     */
    private String toiletId;
    /**
     * 关联sys_complaint_type.id
     */
    private String complaintTypeId;
    /**
     * 投诉内容
     */
    private String content;
    /**
     * 投诉人
     */
    private String complaintName;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 投诉时间
     */
    private LocalDateTime complaintTime;
    /**
     * 派单状态：待派单/已派单/已处置
     */
    private String dispatchStatus;
    /**
     * 关联sys_user.id
     */
    private String handlerId;
    /**
     * 是否超时：是/否
     */
    private String isTimeout;
    /**
     * 处置措施
     */
    private String handleMeasure;
    /**
     * 处置结果
     */
    private String handleResult;
    /**
     * 整改照片URL
     */
    private String reformPhoto;
    /**
     * 反馈内容
     */
    private String feedbackContent;
    /**
     * 通用扩展字段1
     */
    @JsonIgnore
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    @JsonIgnore
    private String extCommon2;
    /**
     * 通用扩展字段3
     */
    @JsonIgnore
    private String extCommon3;
    /**
     * 通用扩展字段4
     */
    @JsonIgnore
    private String extCommon4;

}