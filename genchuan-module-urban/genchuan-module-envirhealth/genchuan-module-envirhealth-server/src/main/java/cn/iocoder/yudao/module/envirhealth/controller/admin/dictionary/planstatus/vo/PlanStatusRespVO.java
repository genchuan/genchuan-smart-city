package cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.planstatus.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 计划状态字典 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PlanStatusRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16239")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "计划状态主键（UUID）", example = "28005")
    @ExcelProperty("计划状态主键（UUID）")
    private String sysPlanStatusId;

    @Schema(description = "状态名称", example = "张三")
    @ExcelProperty("状态名称")
    private String name;

    @Schema(description = "状态编码")
    @ExcelProperty("状态编码")
    private String code;

    @Schema(description = "状态：1-启用/0-禁用", example = "2")
    @ExcelProperty("状态：1-启用/0-禁用")
    private Integer status;

    @Schema(description = "排序")
    @ExcelProperty("排序")
    private Integer sort;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}