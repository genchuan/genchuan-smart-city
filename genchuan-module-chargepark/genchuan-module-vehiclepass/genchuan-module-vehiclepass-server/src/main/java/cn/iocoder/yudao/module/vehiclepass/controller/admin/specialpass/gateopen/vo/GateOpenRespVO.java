package cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 开闸管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GateOpenRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15790")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "场站名称")
    @ExcelProperty("场站名称")
    private String stationName;

    @Schema(description = "开闸原因：紧急通行 / 故障处理 / 其他，关联字典gate_open_open_reason", requiredMode = Schema.RequiredMode.REQUIRED, example = "不对")
    @ExcelProperty("开闸原因：紧急通行 / 故障处理 / 其他，关联字典gate_open_open_reason")
    private String openReason;

    @Schema(description = "申请人姓名")
    @ExcelProperty("申请人姓名")
    private String applyUserName;

    @Schema(description = "申请时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("申请时间")
    private LocalDateTime applyTime;

    @Schema(description = "状态：待审批 / 已通过 / 已驳回 / 已执行，关联字典gate_open_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("状态：待审批 / 已通过 / 已驳回 / 已执行，关联字典gate_open_status")
    private String status;

    @Schema(description = "审批人姓名")
    @ExcelProperty("审批人姓名")
    private String auditUserName;

    @Schema(description = "审批时间")
    @ExcelProperty("审批时间")
    private LocalDateTime auditTime;

    @Schema(description = "执行时间")
    @ExcelProperty("执行时间")
    private LocalDateTime executeTime;

    @Schema(description = "驳回理由", example = "不喜欢")
    @ExcelProperty("驳回理由")
    private String rejectReason;

    @Schema(description = "备注", example = "你说的对")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "更新者")
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}