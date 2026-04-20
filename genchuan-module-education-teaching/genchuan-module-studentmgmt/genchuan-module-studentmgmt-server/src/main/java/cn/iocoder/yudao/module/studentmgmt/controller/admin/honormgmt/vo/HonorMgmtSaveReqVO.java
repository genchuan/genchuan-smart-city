package cn.iocoder.yudao.module.studentmgmt.controller.admin.honormgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 荣誉管理新增/修改 Request VO")
@Data
public class HonorMgmtSaveReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5053")
    private Long id;

    @Schema(description = "学生 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19619")
    @NotNull(message = "学生 ID不能为空")
    private Long studentId;

    @Schema(description = "荣誉类型：优秀学生/奖学金/竞赛获奖/其他", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "荣誉类型：优秀学生/奖学金/竞赛获奖/其他不能为空")
    private String honorType;

    @Schema(description = "荣誉名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "荣誉名称不能为空")
    private String honorName;

    @Schema(description = "获得时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "获得时间不能为空")
    private LocalDateTime getTime;

    @Schema(description = "审核人")
    private String auditUser;

    @Schema(description = "审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "推送时间")
    private LocalDateTime pushTime;

    @Schema(description = "状态：待审核/已通过/已推送", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "状态：待审核/已通过/已推送不能为空")
    private String status;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

}