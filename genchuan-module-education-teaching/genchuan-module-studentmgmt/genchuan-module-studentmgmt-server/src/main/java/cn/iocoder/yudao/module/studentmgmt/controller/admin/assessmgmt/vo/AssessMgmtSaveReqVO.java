package cn.iocoder.yudao.module.studentmgmt.controller.admin.assessmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 考评管理新增/修改 Request VO")
@Data
public class AssessMgmtSaveReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "班级", requiredMode = Schema.RequiredMode.REQUIRED, example = "2024级软件工程1班")
    @NotEmpty(message = "班级不能为空")
    private String className;

    @Schema(description = "考评类型：教室卫生/早操/文明班级/黑板报", requiredMode = Schema.RequiredMode.REQUIRED, example = "class_clean")
    @NotEmpty(message = "考评类型：教室卫生/早操/文明班级/黑板报不能为空")
    private String assessType;

    @Schema(description = "统计周期：周/月/学期", requiredMode = Schema.RequiredMode.REQUIRED, example = "week")
    @NotEmpty(message = "统计周期：周/月/学期不能为空")
    private String cycle;

    @Schema(description = "考评得分", requiredMode = Schema.RequiredMode.REQUIRED, example = "90.5")
    @NotNull(message = "考评得分不能为空")
    private BigDecimal score;

    @Schema(description = "班级排名")
    private Integer rankNo;

    @Schema(description = "考评人")
    private String assessUser;

    @Schema(description = "发布时间")
    private LocalDateTime publishTime;

    @Schema(description = "状态：未发布/已发布", requiredMode = Schema.RequiredMode.REQUIRED, example = "un_publish")
    @NotEmpty(message = "状态：未发布/已发布不能为空")
    private String status;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

}