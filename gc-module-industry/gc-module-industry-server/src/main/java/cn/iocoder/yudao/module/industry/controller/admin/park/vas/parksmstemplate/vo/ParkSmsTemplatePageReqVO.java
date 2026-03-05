package cn.iocoder.yudao.module.industry.controller.admin.park.vas.parksmstemplate.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 短信模板分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkSmsTemplatePageReqVO extends PageParam {

    @Schema(description = "[模板编码] 短信模板编码")
    private String templateCode;

    @Schema(description = "[模板名称] 短信模板名称", example = "王五")
    private String templateName;

    @Schema(description = "[短信类型] 如：验证短信/到期提醒/缴费通知/活动通知/投诉反馈", example = "1")
    private String smsType;

    @Schema(description = "[模板内容] 短信模板内容，包含占位符")
    private String content;

    @Schema(description = "[状态] 如：禁用/启用", example = "1")
    private String status;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[备注] 短信模板备注说明", example = "你说的对")
    private String remark;

    @Schema(description = "[通用扩展字段1] 通用扩展字段1")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 通用扩展字段2")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 通用扩展字段3")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 通用扩展字段4")
    private String extCommon4;

}
