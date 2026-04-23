package cn.iocoder.yudao.module.studentmgmt.controller.admin.studentinfo.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 查询学生信息分布看板的核心统计数据 Response VO")
@Data
@ExcelIgnoreUnannotated
public class StudentInfoDistributionCountRespVO {

    @Schema(description = "维度名称，如年级 / 专业 / 班级名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("维度名称")
    private String name;

    @Schema(description = "该维度的学生数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("该维度的学生数量")
    private Integer count;
}
