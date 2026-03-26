package cn.iocoder.yudao.module.waterdetection.controller.admin.issuetracking.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 问题上报与闭环跟踪 Response VO")
@Data
@ExcelIgnoreUnannotated
public class IssueTrackingRespVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("序号")
    private Long id;

    @Schema(description = "问题ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("问题ID")
    private String issueId;

    @Schema(description = "问题类型(漏点/设备故障/标识牌损坏)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("问题类型(漏点/设备故障/标识牌损坏)")
    private String issueType;

    @Schema(description = "上报时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("上报时间")
    private LocalDateTime reportTime;

    @Schema(description = "派单时间")
    @ExcelProperty("派单时间")
    private LocalDateTime dispatchTime;

    @Schema(description = "维修人员ID")
    @ExcelProperty("维修人员ID")
    private String repairStaffId;

    @Schema(description = "修复时间")
    @ExcelProperty("修复时间")
    private LocalDateTime repairTime;

    @Schema(description = "验收结果")
    @ExcelProperty("验收结果")
    private String inspectionResult;

    @Schema(description = "闭环状态")
    @ExcelProperty("闭环状态")
    private String closureStatus;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}