package cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parksmstemplate;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 短信模板 DO
 *
 * @author lxs
 */
@TableName("park_sms_template")
@KeySequence("park_sms_template_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkSmsTemplateDO extends BaseDO {

    /**
     * [主键ID] 短信模板唯一标识
     */
    @TableId
    private Long id;
    /**
     * [模板编码] 短信模板编码
     */
    private String templateCode;
    /**
     * [模板名称] 短信模板名称
     */
    private String templateName;
    /**
     * [短信类型] 如：验证短信/到期提醒/缴费通知/活动通知/投诉反馈
     */
    private String smsType;
    /**
     * [模板内容] 短信模板内容，包含占位符
     */
    private String content;
    /**
     * [状态] 如：禁用/启用
     */
    private String status;
    /**
     * [备注] 短信模板备注说明
     */
    private String remark;
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

}
