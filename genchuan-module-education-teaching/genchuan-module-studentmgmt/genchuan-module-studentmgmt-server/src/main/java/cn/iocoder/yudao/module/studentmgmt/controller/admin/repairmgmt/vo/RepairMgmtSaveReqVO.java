package cn.iocoder.yudao.module.studentmgmt.controller.admin.repairmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 报修管理新增/修改 Request VO")
@Data
public class RepairMgmtSaveReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5870")
    private Long id;

    @Schema(description = "宿舍号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "宿舍号不能为空")
    private String dormNum;

    @Schema(description = "报修类型：水电/家具/其他", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "报修类型：水电/家具/其他不能为空")
    private String repairType;

    @Schema(description = "申请时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "申请时间不能为空")
    private LocalDateTime applyTime;

    @Schema(description = "派单人")
    private String dispatchUser;

    @Schema(description = "派单时间")
    private LocalDateTime dispatchTime;

    @Schema(description = "维修人")
    private String repairUser;

    @Schema(description = "维修反馈")
    private String feedbackContent;

    @Schema(description = "反馈时间")
    private LocalDateTime feedbackTime;

    @Schema(description = "验收人")
    private String checkUser;

    @Schema(description = "验收时间")
    private LocalDateTime checkTime;

    @Schema(description = "状态：待派单/维修中/已维修", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "状态：待派单/维修中/已维修不能为空")
    private String status;

    @Schema(description = "验收状态：未验收/已验收", example = "2")
    private String checkStatus;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

}