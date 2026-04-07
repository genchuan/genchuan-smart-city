package cn.iocoder.yudao.module.kitchen.controller.admin.violationanalytics.vo.page;



import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Schema(description = "企业风险报表 Request VO")
public class ViolationAnalyticsPageReq extends PageParam {

    // ====================== 【必填：区分月报 / 自定义报表】 ======================
//    @Schema(description = "报表类型 1-企业月度评估报告 2-自定义报表", requiredMode = Schema.RequiredMode.REQUIRED)
//    private Integer reportType;

    // ====================== 【统计周期是月的】 ======================
    @Schema(description = "统计周期(当日/周/月)")
    private String statisticPeriod;


    // ====================== 【通用时间条件】 ======================
    @Schema(description = "统计开始时间")
    private LocalDate beginTime;

    @Schema(description = "统计结束时间")
    private LocalDate endTime;

    // ====================== 【企业维度】 ======================
//    @Schema(description = "企业ID")
//    private Long entId;

//    @Schema(description = "企业名称（模糊查询）")
//    private String entName;





}
