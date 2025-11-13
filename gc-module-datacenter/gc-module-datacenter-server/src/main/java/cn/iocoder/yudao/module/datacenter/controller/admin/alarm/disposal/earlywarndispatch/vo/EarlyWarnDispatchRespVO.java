package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarndispatch.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 预警告警派单 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EarlyWarnDispatchRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "派单ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("派单ID")
    private String dispatchId;

    @Schema(description = "派单编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("派单编号")
    private String dispatchNo;

    @Schema(description = "预警ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("预警ID")
    private String earlyWarnId;

    @Schema(description = "预警名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("预警名称")
    private String earlyWarnName;

    @Schema(description = "派单类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("派单类型")
    private String dispatchType;

    @Schema(description = "接收部门ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("接收部门ID")
    private String receiveDeptId;

    @Schema(description = "接收部门名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("接收部门名称")
    private String receiveDeptName;

    @Schema(description = "处置人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("处置人ID")
    private String handleUserId;

    @Schema(description = "处置人姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("处置人姓名")
    private String handleUserName;

    @Schema(description = "派单状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("派单状态")
    private String dispatchStatus;

    @Schema(description = "处置时限", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("处置时限")
    private Integer handleLimit;

    @Schema(description = "派单时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("派单时间")
    private LocalDateTime dispatchTime;

    @Schema(description = "接收时间")
    @ExcelProperty("接收时间")
    private LocalDateTime receiveTime;

    @Schema(description = "拒单原因")
    @ExcelProperty("拒单原因")
    private String rejectReason;

    @Schema(description = "派单人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("派单人ID")
    private String dispatchUserId;

    @Schema(description = "派单人姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("派单人姓名")
    private String dispatchUserName;

    @Schema(description = "分类扩展字段1")
    @ExcelProperty("分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    @ExcelProperty("分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}