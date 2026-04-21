package cn.iocoder.yudao.module.studentmgmt.controller.admin.assessmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Schema(description = "管理后台 - 考评班级多维度考评得分统计 Request VO")
@Data
public class AssessMgmtDimensionScoreReqVO {
    @Schema(description = "统计周期，可选周 / 月 / 学期，默认当前月。")
    private String cycle;

}