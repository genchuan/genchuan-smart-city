package cn.iocoder.yudao.module.smartcity.controller.admin.personnelhomework.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 人员作业新增/修改 Request VO")
@Data
public class PersonnelHomeworkSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "19217")
    private Long id;

    @Schema(description = "作业编号")
    private String jobNumber;

    @Schema(description = "作业人员编号", example = "6884")
    private String operatorId;

    @Schema(description = "作业区域编号")
    private String assignmentAreaNumber;

    @Schema(description = "作业时间")
    private LocalDateTime operationTime;

    @Schema(description = "作业内容")
    private String homeworkContent;

    @Schema(description = "作业方式")
    private String operationMode;

    @Schema(description = "备注")
    private String notes;

}