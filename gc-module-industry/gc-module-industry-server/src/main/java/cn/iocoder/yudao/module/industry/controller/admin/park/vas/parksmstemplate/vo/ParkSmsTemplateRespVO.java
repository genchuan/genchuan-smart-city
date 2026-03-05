package cn.iocoder.yudao.module.industry.controller.admin.park.vas.parksmstemplate.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 短信模板 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkSmsTemplateRespVO {

    @Schema(description = "[主键ID] 短信模板唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "2797")
    @ExcelProperty("[主键ID] 短信模板唯一标识")
    private Long id;

    @Schema(description = "[模板编码] 短信模板编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[模板编码] 短信模板编码")
    private String templateCode;

    @Schema(description = "[模板名称] 短信模板名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("[模板名称] 短信模板名称")
    private String templateName;

    @Schema(description = "[短信类型] 如：验证短信/到期提醒/缴费通知/活动通知/投诉反馈", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("[短信类型] 如：验证短信/到期提醒/缴费通知/活动通知/投诉反馈")
    private String smsType;

    @Schema(description = "[模板内容] 短信模板内容，包含占位符", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[模板内容] 短信模板内容，包含占位符")
    private String content;

    @Schema(description = "[状态] 如：禁用/启用", example = "1")
    @ExcelProperty("[状态] 如：禁用/启用")
    private String status;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[备注] 短信模板备注说明", example = "你说的对")
    @ExcelProperty("[备注] 短信模板备注说明")
    private String remark;

    @Schema(description = "[通用扩展字段1] 通用扩展字段1")
    @ExcelProperty("[通用扩展字段1] 通用扩展字段1")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 通用扩展字段2")
    @ExcelProperty("[通用扩展字段2] 通用扩展字段2")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 通用扩展字段3")
    @ExcelProperty("[通用扩展字段3] 通用扩展字段3")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 通用扩展字段4")
    @ExcelProperty("[通用扩展字段4] 通用扩展字段4")
    private String extCommon4;

}
