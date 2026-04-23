package cn.iocoder.yudao.module.studentmgmt.controller.admin.honormgmt.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 荣誉管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class HonorMgmtPageRespVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5053")
    @ExcelProperty("主键 ID")
    private Long id;

    @Schema(description = "学生 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19619")
    @ExcelProperty("学生 ID")
    private Long studentId;

    @Schema(description = "学生姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("学生姓名")
    private String studentName;

    @Schema(description = "班级名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "1班")
    @ExcelProperty("班级名称")
    private String className;

    @Schema(description = "荣誉类型：优秀学生/奖学金/竞赛获奖/其他", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("荣誉类型：优秀学生/奖学金/竞赛获奖/其他")
    private String honorType;

    @Schema(description = "荣誉名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("荣誉名称")
    private String honorName;

    @Schema(description = "获得时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("获得时间")
    private LocalDateTime getTime;

    @Schema(description = "审核人")
    @ExcelProperty("审核人")
    private String auditUser;

    @Schema(description = "审核时间")
    @ExcelProperty("审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "推送时间")
    @ExcelProperty("推送时间")
    private LocalDateTime pushTime;

    @Schema(description = "状态：待审核/已通过/已推送", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("状态：待审核/已通过/已推送")
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
