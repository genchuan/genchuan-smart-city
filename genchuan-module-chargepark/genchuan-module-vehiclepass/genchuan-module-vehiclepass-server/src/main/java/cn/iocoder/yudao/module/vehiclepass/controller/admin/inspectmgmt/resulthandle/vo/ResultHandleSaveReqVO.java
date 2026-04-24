package cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.resulthandle.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 结果处置新增/修改 Request VO")
@Data
public class ResultHandleSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8467")
    private Long id;

    @Schema(description = "关联任务ID，关联稽查任务表inspect_task", requiredMode = Schema.RequiredMode.REQUIRED, example = "3993")
    @NotNull(message = "关联任务ID，关联稽查任务表inspect_task不能为空")
    private Long taskId;

    @Schema(description = "违规类型：违规通行/欠费逃费/其他，关联字典result_handle_violation_type", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "违规类型：违规通行/欠费逃费/其他，关联字典result_handle_violation_type不能为空")
    private String violationType;

    @Schema(description = "处置方式：补缴费用/限制入场/警告/其他，关联字典result_handle_handle_method", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "处置方式：补缴费用/限制入场/警告/其他，关联字典result_handle_handle_method不能为空")
    private String handleMethod;

    @Schema(description = "状态：待审核/待处置/已完成/已驳回，关联字典result_handle_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "状态：待审核/待处置/已完成/已驳回，关联字典result_handle_status不能为空")
    private String status;

    @Schema(description = "片区ID，关联片区表", example = "16056")
    private Long areaId;

    @Schema(description = "处置人ID，关联芋道用户表system_user", example = "15936")
    private Long handleUserId;

    @Schema(description = "处置时间")
    private LocalDateTime handleTime;

    @Schema(description = "整改状态：未整改/已整改，关联字典result_handle_rectify_status", example = "2")
    private String rectifyStatus;

    @Schema(description = "驳回理由", example = "不好")
    private String rejectReason;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}