package cn.iocoder.yudao.module.kitchen.controller.admin.violationanalytics.vo.drill;



import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Schema(description = "企业风险报表 Request VO")
public class ViolationAnalyticsDrillReq extends PageParam {

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
    @Schema(description = "企业ID")
    @NotNull(message = "企业id不能为空")
    private Long entId;

    // ====================== 【钻取维度】 ======================

    @Schema(description = "钻取纬度,值有告警/违规/正常设备/整改完成。null表示全选")
    private String drillDimension;
//    @Schema(description = "企业名称（模糊查询）")
//    private String entName;





}
