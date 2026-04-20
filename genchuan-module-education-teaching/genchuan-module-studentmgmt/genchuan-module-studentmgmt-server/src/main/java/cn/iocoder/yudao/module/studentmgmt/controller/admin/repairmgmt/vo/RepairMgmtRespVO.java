package cn.iocoder.yudao.module.studentmgmt.controller.admin.repairmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 报修管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class RepairMgmtRespVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5870")
    @ExcelProperty("主键 ID")
    private Long id;

    @Schema(description = "宿舍号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("宿舍号")
    private String dormNum;

    @Schema(description = "报修类型：水电/家具/其他", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("报修类型：水电/家具/其他")
    private String repairType;

    @Schema(description = "申请时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("申请时间")
    private LocalDateTime applyTime;

    @Schema(description = "派单人")
    @ExcelProperty("派单人")
    private String dispatchUser;

    @Schema(description = "派单时间")
    @ExcelProperty("派单时间")
    private LocalDateTime dispatchTime;

    @Schema(description = "维修人")
    @ExcelProperty("维修人")
    private String repairUser;

    @Schema(description = "维修反馈")
    @ExcelProperty("维修反馈")
    private String feedbackContent;

    @Schema(description = "反馈时间")
    @ExcelProperty("反馈时间")
    private LocalDateTime feedbackTime;

    @Schema(description = "验收人")
    @ExcelProperty("验收人")
    private String checkUser;

    @Schema(description = "验收时间")
    @ExcelProperty("验收时间")
    private LocalDateTime checkTime;

    @Schema(description = "状态：待派单/维修中/已维修", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("状态：待派单/维修中/已维修")
    private String status;

    @Schema(description = "验收状态：未验收/已验收", example = "2")
    @ExcelProperty("验收状态：未验收/已验收")
    private String checkStatus;

    @Schema(description = "备注", example = "你猜")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "备用字段 1")
    @ExcelProperty("备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    @ExcelProperty("备用字段 2")
    private String reserve2;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}