package cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.operationstatus.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 运营状态字典 Response VO")
@Data
@ExcelIgnoreUnannotated
public class OperationStatusRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10177")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "业务主键（UUID）", example = "26423")
    @ExcelProperty("业务主键（UUID）")
    private String sysOperationStatusId;

    @Schema(description = "状态名称（如正常运营/暂停开放/维修中）", example = "芋艿")
    @ExcelProperty("状态名称（如正常运营/暂停开放/维修中）")
    private String name;

    @Schema(description = "状态编码")
    @ExcelProperty("状态编码")
    private String code;

    @Schema(description = "状态：启用/禁用", example = "1")
    @ExcelProperty("状态：启用/禁用")
    private Integer status;

    @Schema(description = "排序号")
    @ExcelProperty("排序号")
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