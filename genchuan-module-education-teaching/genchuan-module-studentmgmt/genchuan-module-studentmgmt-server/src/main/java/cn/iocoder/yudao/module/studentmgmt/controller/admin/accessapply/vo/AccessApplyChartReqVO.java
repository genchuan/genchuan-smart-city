package cn.iocoder.yudao.module.studentmgmt.controller.admin.accessapply.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 审核 Request VO")
@Data
public class AccessApplyChartReqVO {

    @Schema(description = "统计时间范围", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime[] timeRange;

    @Schema(description = "班级Id", example = "1")
    private Long classId;


}