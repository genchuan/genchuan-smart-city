package cn.iocoder.yudao.module.smartcity.controller.admin.inspectionresultsa.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 巡查结果新增/修改 Request VO")
@Data
public class InspectionResultsASaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "17992")
    private Long id;

    @Schema(description = "编号")
    private String number;

    @Schema(description = "巡查人员")
    private String patrolPersonnel;

    @Schema(description = "巡查时间")
    private LocalDateTime patrolTime;

    @Schema(description = "巡查地点")
    private String patrolLocation;

    @Schema(description = "资源名称", example = "赵六")
    private String resourceName;

    @Schema(description = "资源编号")
    private String resourceNumber;

    @Schema(description = "检查项目")
    private String inspectionItems;

    @Schema(description = "检查结果")
    private String inspectionResults;

}