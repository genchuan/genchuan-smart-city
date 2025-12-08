package cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.fltsite.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 市政设施专题-故障设施空间数据 Response VO")
@Data
public class FltSiteRespVO {

    @Schema(description = "设施ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "MF-001")
    @ExcelProperty("设施ID")
    private String mngCompId;

    @Schema(description = "设施名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "五四北路井盖破损")
    @ExcelProperty("设施名称")
    private String compName;

    @Schema(description = "设施状态", example = "紧急故障")
    @ExcelProperty("设施状态")
    private String compStatus;

    //麻烦点
    @Schema(description = "故障原因", example = "重型车辆碾压导致井盖断裂，影响行人安全")
    @ExcelProperty("故障原因")
    private String faultReason;

    @Schema(description = "坐标X", example = "116.391")
    @ExcelProperty("坐标X")
    private Double coordX;

    @Schema(description = "坐标Y", example = "39.906")
    @ExcelProperty("坐标Y")
    private Double coordY;

    @Schema(description = "设施类型", example = "井盖")
    @ExcelProperty("设施类型")
    private String compType;

    @Schema(description = "上报时间", example = "2025-12-03 08:15")
    @ExcelProperty("上报时间")
    private LocalDateTime reportTime;

    @Schema(description = "处理人", example = "")
    @ExcelProperty("处理人")
    private String handler;

    @Schema(description = "处理状态", example = "未处置")
    @ExcelProperty("处理状态")
    private String handleStatus;

    @Schema(description = "更新时间", example = "2025-12-03 08:15")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "是否超时（超过24小时）", example = "true")
    @ExcelProperty("是否超时")
    private Boolean isTimeout;
}
