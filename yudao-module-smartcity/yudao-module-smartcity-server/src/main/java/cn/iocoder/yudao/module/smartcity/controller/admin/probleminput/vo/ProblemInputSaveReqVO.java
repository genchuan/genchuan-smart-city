package cn.iocoder.yudao.module.smartcity.controller.admin.probleminput.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 问题录入新增/修改 Request VO")
@Data
public class ProblemInputSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "24767")
    private Long id;

    @Schema(description = "问题标题")
    private String questionTitle;

    @Schema(description = "问题描述", example = "你猜")
    private String problemDescription;

    @Schema(description = "提问时间")
    private LocalDateTime questionTime;

    @Schema(description = "提问人")
    private String questioner;

    @Schema(description = "所属领域")
    private String isArea;

    @Schema(description = "紧急程度")
    private String urgency;

    @Schema(description = "问题类型", example = "1")
    private String questionType;

}