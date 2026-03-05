package cn.iocoder.yudao.module.envirhealth.controller.admin.urbanvillage.vo.reviewresult;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 复核结果字典表【通用复用】 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ReviewResultRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "23889")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "主键（UUID）", example = "6524")
    @ExcelProperty("主键（UUID）")
    private String reviewResultId;

    @Schema(description = "复核结果名称：通过/不通过", example = "张三")
    @ExcelProperty("复核结果名称：通过/不通过")
    private String reviewResultName;

    @Schema(description = "描述", example = "你说的对")
    @ExcelProperty("描述")
    private String description;

    @Schema(description = "状态（可选值：0-禁用/1-启用）", example = "1")
    @ExcelProperty("状态（可选值：0-禁用/1-启用）")
    private Integer status;

    @Schema(description = "排序值")
    @ExcelProperty("排序值")
    private Integer sort;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    @ExcelProperty("通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    @ExcelProperty("通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}