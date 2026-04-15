package cn.iocoder.yudao.module.studentmgmt.controller.admin.behaviormgmt.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 学生心理健康看板 Request VO")
@Data
@ExcelIgnoreUnannotated
public class BehaviorMgmtChartReqVO {

    @Schema(description = "统计时间范围")
    @ExcelProperty("统计时间范围")
    private LocalDateTime[] timeRange;

    @Schema(description = "班级ID")
    @ExcelProperty("班级ID")
    private Long classId ;
}
