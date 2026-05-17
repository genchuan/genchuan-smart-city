package cn.iocoder.yudao.module.studentmgmt.controller.admin.workhome.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 学工综合数据看板 Response VO")
@Data
public class WorkHomeScoreAnalysisRespVO {

    @Schema(description = "班级名称")
    private String className;
    @Schema(description = "教室卫生得分")
    private String healthScore;
    @Schema(description = "早操得分")
    private String exerciseScore;
    @Schema(description = "文明班级得分")
    private String civilizedScore;
    @Schema(description = "黑板报得分")
    private String blackboardScore;
    @Schema(description = "综合总分")
    private String totalScore;
}