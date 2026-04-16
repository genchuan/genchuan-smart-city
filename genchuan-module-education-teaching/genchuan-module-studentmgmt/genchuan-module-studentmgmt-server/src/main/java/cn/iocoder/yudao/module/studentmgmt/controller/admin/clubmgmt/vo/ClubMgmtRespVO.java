package cn.iocoder.yudao.module.studentmgmt.controller.admin.clubmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 社团管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ClubMgmtRespVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "23257")
    @ExcelProperty("主键 ID")
    private Long id;

    @Schema(description = "社团名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("社团名称")
    private String clubName;

    @Schema(description = "社团类型：文体/学术/志愿/其他", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("社团类型：文体/学术/志愿/其他")
    private String clubType;

    @Schema(description = "学生 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15619")
    @ExcelProperty("学生 ID")
    private Long studentId;

    @Schema(description = "入团申请时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("入团申请时间")
    private LocalDateTime applyTime;

    @Schema(description = "审核人")
    @ExcelProperty("审核人")
    private String auditUser;

    @Schema(description = "审核时间")
    @ExcelProperty("审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "建档时间")
    @ExcelProperty("建档时间")
    private LocalDateTime archiveTime;

    @Schema(description = "场馆申请状态：无/待申请/已通过", example = "2")
    @ExcelProperty("场馆申请状态：无/待申请/已通过")
    private String venueApplyStatus;

    @Schema(description = "状态：待审核/已通过/已建档", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("状态：待审核/已通过/已建档")
    private String status;

    @Schema(description = "备注", example = "随便")
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