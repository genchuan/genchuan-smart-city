package cn.iocoder.yudao.module.kitchen.controller.admin.riskreport.vo.page;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "企业风险报表 Request VO")
public class EntReportPageResp {
    // ====================== 【必填：区分月报 / 自定义报表】 ======================
    @Schema(description = "报表类型 1-企业月度评估报告 2-自定义报表", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer reportType;

    // ====================== 【月报专用】 ======================
    @Schema(description = "统计周期（yyyy-MM）：仅月报使用")
    private String statisticPeriod;

    // ====================== 【AI文档字段】 ======================
    @Schema(description = "报告编号")
    private String reportNo;

    // ====================== 【企业维度(月报专有）】 ======================
    @Schema(description = "企业ID")
    private Long entId;

    @Schema(description = "企业名称（模糊查询）")
    private String entName;

    // ====================== 【通用】 ======================
    @Schema(description = "风险等级")
    private String riskLevel;

    @Schema(description = "区域")
    private String area;

    @Schema(description = "企业类型")
    private String entType;

    @Schema(description = "统计开始时间")
    private LocalDate beginTime;

    @Schema(description = "统计结束时间")
    private LocalDate endTime;

    @Schema(description = "违规次数")
    private String violationCount;


}
