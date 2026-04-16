package cn.iocoder.yudao.module.kitchen.controller.admin.violationanalytics.vo.page;



import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(description = "企业风险报表 Request VO")
public class ViolationAnalyticsPageReq extends PageParam {

    // ====================== 【必填：区分月报 / 自定义报表】 ======================
//    @Schema(description = "报表类型 1-企业月度评估报告 2-自定义报表", requiredMode = Schema.RequiredMode.REQUIRED)
//    private Integer reportType;

    // ====================== 【统计周期是月的】 ======================
    @Schema(description = "统计周期(日/周/月)")
    private String statisticPeriod;


    // ====================== 【通用时间条件】 ======================
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "统计开始时间", example = "2026-04-01 00:00:00",hidden = true)
    private LocalDateTime beginTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "统计结束时间", example = "2026-04-07 23:59:59",hidden = true)
    private LocalDateTime endTime;

    // ====================== 【企业维度】 ======================
//    @Schema(description = "企业ID")
//    private Long entId;

//    @Schema(description = "企业名称（模糊查询）")
//    private String entName;





}
