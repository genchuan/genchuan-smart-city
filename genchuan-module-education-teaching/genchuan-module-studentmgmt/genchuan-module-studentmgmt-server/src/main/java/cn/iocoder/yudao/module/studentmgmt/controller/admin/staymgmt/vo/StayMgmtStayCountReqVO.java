package cn.iocoder.yudao.module.studentmgmt.controller.admin.staymgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 各班级留宿人数统计 Request VO")
@Data
public class StayMgmtStayCountReqVO {

    @Schema(description = "统计时间范围", requiredMode = Schema.RequiredMode.REQUIRED, example = "10528")
    private LocalDateTime[] timeRange ;

}