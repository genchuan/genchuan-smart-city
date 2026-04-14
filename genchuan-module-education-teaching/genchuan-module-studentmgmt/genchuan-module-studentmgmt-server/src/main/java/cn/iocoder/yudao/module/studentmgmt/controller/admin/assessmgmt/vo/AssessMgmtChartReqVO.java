package cn.iocoder.yudao.module.studentmgmt.controller.admin.assessmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Schema(description = "管理后台 - 班级考评态势看板 Request VO")
@Data
public class AssessMgmtChartReqVO {
    @Schema(description = "统计周期，可选周 / 月 / 学期，默认当前月")
    private String cycle;



}