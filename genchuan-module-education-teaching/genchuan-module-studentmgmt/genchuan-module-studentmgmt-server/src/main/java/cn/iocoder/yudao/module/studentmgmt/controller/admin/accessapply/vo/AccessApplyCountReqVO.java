package cn.iocoder.yudao.module.studentmgmt.controller.admin.accessapply.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 各班级申请次数 / 类型分布统计 Request VO")
@Data
public class AccessApplyCountReqVO {

    @Schema(description = "统计时间范围", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime[] timeRange;

}