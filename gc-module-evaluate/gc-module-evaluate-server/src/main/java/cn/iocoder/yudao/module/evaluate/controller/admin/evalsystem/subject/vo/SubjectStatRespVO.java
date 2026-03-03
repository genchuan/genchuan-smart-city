package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.subject.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 评价主体统计指标 Response VO")
@Data
public class SubjectStatRespVO {
    @Schema(description = "总主体数", example = "100")
    private Integer totalCount;

    @Schema(description = "人工主体数", example = "70")
    private Integer manualSubjectCount;

    @Schema(description = "系统主体数", example = "30")
    private Integer systemSubjectCount;

    @Schema(description = "启用状态主体数", example = "85")
    private Integer enabledCount;
}