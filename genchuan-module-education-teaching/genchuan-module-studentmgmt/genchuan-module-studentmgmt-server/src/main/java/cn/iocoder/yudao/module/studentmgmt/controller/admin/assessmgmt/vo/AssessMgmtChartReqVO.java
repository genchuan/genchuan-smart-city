package cn.iocoder.yudao.module.studentmgmt.controller.admin.assessmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Schema(description = "管理后台 - 考评管理推送 Request VO")
@Data
public class AssessMgmtChartReqVO {
    @Schema(description = "年级")
    private String grade;
    @Schema(description = "专业")
    private String major;



}