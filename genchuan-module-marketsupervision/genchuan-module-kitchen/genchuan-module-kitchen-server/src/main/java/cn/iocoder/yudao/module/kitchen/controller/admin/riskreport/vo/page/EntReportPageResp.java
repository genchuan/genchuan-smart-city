package cn.iocoder.yudao.module.kitchen.controller.admin.riskreport.vo.page;

import cn.idev.excel.annotation.ExcelIgnore;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "企业风险报表 Request VO")
public class EntReportPageResp {
    // ====================== 【必填：区分月报 / 自定义报表】 ======================
//    @ExcelProperty(value = "报表类型", index = 0)
    @ExcelIgnore
    @Schema(description = "报表类型 1-企业月度评估报告 2-自定义报表", hidden = true)
    private Integer reportType;

    // ====================== 【月报专用】 ======================
    @ExcelProperty(value = "统计周期", index = 1)
    @Schema(description = "统计周期（yyyy-MM）：仅月报使用")
    private String statisticPeriod;

    // ====================== 【AI文档字段】 ======================
    @ExcelProperty(value = "报告编号", index = 2)
    @Schema(description = "报告编号")
    private String reportNo;

    // ====================== 【企业维度(月报专有）】 ======================
    @ExcelProperty(value = "企业ID", index = 3)
    @Schema(description = "企业ID")
    private Long entId;

    @ExcelProperty(value = "企业名称", index = 4)
    @Schema(description = "企业名称（模糊查询）")
    private String entName;

    // ====================== 【通用】 ======================
    @ExcelProperty(value = "风险等级", index = 5)
    @Schema(description = "风险等级")
    private String riskLevel;

    @ExcelProperty(value = "区域", index = 6)
    @Schema(description = "区域")
    private String area;

    @ExcelProperty(value = "企业类型", index = 7)
    @Schema(description = "企业类型")
    private String entType;

    @ExcelProperty(value = "统计开始时间", index = 8)
    @Schema(description = "统计开始时间")
    private LocalDate beginTime;

    @ExcelProperty(value = "统计结束时间", index = 9)
    @Schema(description = "统计结束时间")
    private LocalDate endTime;

    @ExcelProperty(value = "违规次数", index = 10)
    @Schema(description = "违规次数")
    private Integer violationCount;
}
