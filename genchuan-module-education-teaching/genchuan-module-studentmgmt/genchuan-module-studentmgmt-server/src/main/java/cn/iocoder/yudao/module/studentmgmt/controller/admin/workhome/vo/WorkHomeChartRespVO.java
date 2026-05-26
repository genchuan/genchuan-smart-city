package cn.iocoder.yudao.module.studentmgmt.controller.admin.workhome.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 学工综合数据看板 Response VO")
@Data
public class WorkHomeChartRespVO {

    @Schema(description = "学生总数")
    private Integer totalStudent;
    @Schema(description = "荣誉记录总数")
    private Integer totalHonor;
    @Schema(description = "考评记录总数")
    private Integer totalAssess;
    @Schema(description = "违纪记录总数")
    private Integer totalViolate;
    @Schema(description = "心理评估记录总数")
    private Integer totalMental;
    @Schema(description = "资助记录总数")
    private Integer totalFund;
    @Schema(description = "待处理违纪数")
    private Integer unhandledViolate;
    @Schema(description = "待处理预警数")
    private Integer unhandledWarn;

}