package cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parkcomplaint;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 投诉记录 DO
 *
 * @author lxs
 */
@TableName("park_complaint")
@KeySequence("park_complaint_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkComplaintDO extends BaseDO {

    /**
     * [主键ID] 投诉记录唯一标识
     */
    @TableId
    private Long id;
    /**
     * [投诉编号] 投诉唯一编号
     */
    private String complaintNo;
    /**
     * [投诉人ID] 投诉人唯一标识
     */
    private Long complainantId;
    /**
     * [投诉人电话] 投诉人联系电话
     */
    private String complainantPhone;
    /**
     * [投诉类型] 如：服务投诉/设备故障/收费争议/其他
     */
    private String complaintType;
    /**
     * [关联资产ID] 关联资产唯一标识
     */
    private Long relatedAssetId;
    /**
     * [关联订单ID] 关联订单唯一标识
     */
    private Long relatedOrderId;
    /**
     * [投诉内容] 投诉具体内容
     */
    private String complaintContent;
    /**
     * [投诉时间] 用户发起投诉的时间
     */
    private LocalDateTime complaintTime;
    /**
     * [处理状态] 如：待处理/处理中/已办结/已驳回
     */
    private String status;
    /**
     * [处理内容] 投诉处理结果及说明
     */
    private String processContent;
    /**
     * [处理人] 投诉处理人唯一标识
     */
    private Long processBy;
    /**
     * [处理时间] 投诉处理完成时间
     */
    private LocalDateTime processTime;
    /**
     * [满意度] 如：非常满意/满意/一般/不满意/非常不满意
     */
    private String satisfaction;
    /**
     * [反馈时间] 用户反馈时间
     */
    private LocalDateTime feedbackTime;
    /**
     * [通用扩展字段1] 通用扩展字段1
     */
    private String extCommon1;
    /**
     * [通用扩展字段2] 通用扩展字段2
     */
    private String extCommon2;
    /**
     * [通用扩展字段3] 通用扩展字段3
     */
    private String extCommon3;
    /**
     * [通用扩展字段4] 通用扩展字段4
     */
    private String extCommon4;
    /**
     * [备注] 投诉相关备注说明
     */
    private String remark;

}
