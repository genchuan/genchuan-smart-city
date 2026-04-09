package cn.iocoder.yudao.module.studentmgmt.controller.admin.assessmgmt.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 考评管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AssessMgmtTypeCountRespVO {

    @Schema(description = "考评类型名称")
    @ExcelProperty("考评类型名称")
    private String name;
    @Schema(description = "该类型的考评记录数量")
    @ExcelProperty("该类型的考评记录数量")
    private Integer count;



}
