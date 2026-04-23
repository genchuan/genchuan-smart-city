package cn.iocoder.yudao.module.studentmgmt.controller.admin.honormgmt.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Schema(description = "管理后台 - 各班级 / 类型荣誉数量统计 Response VO")
@Data
@ExcelIgnoreUnannotated
public class HonorCountRespVO {

    @Schema(description = "维度名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private String name;
    @Schema(description = "荣誉数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    private Integer count;

}
