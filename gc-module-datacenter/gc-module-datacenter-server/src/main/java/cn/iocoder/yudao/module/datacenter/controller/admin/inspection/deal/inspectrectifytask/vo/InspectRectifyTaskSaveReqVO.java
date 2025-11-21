package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.deal.inspectrectifytask.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 巡查巡检整改任务派发新增/修改 Request VO")
@Data
public class InspectRectifyTaskSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "整改任务ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "整改任务ID不能为空")
    private String rectifyTaskId;

    @Schema(description = "整改任务编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "整改任务编码不能为空")
    private String rectifyTaskCode;

    @Schema(description = "关联问题ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联问题ID不能为空")
    private String problemId;

    @Schema(description = "关联问题编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联问题编码不能为空")
    private String problemCode;

    @Schema(description = "关联问题名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联问题名称不能为空")
    private String problemName;

    @Schema(description = "责任部门代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "责任部门代码不能为空")
    private String deptCode;

    @Schema(description = "责任部门名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "责任部门名称不能为空")
    private String deptName;

    @Schema(description = "整改负责人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "整改负责人ID不能为空")
    private String leaderUserId;

    @Schema(description = "整改负责人姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "整改负责人姓名不能为空")
    private String leaderUserName;

    @Schema(description = "整改时限（小时）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "整改时限（小时）不能为空")
    private Integer rectifyDeadlineHour;

    @Schema(description = "整改截止时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "整改截止时间不能为空")
    private LocalDateTime rectifyDeadlineTime;

    @Schema(description = "派发说明")
    private String dispatchDesc;

    @Schema(description = "整改任务状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "整改任务状态不能为空")
    private String rectifyStatus;

    @Schema(description = "派发人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "派发人不能为空")
    private String dispatchUser;

    @Schema(description = "派发时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "派发时间不能为空")
    private LocalDateTime dispatchTime;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "创建时间不能为空")
    private LocalDateTime bizCreateTime;

    @Schema(description = "更新时间")
    private LocalDateTime bizUpdateTime;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}