package cn.iocoder.yudao.module.studentmgmt.controller.admin.repairmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 报修类型 / 维修完成率统计 Request VO")
@Data
public class RepairMgmtCountReqVO {

    @Schema(description = "统计时间范围", requiredMode = Schema.RequiredMode.REQUIRED, example = "时间范围参数需要符合yyyy-MM-dd HH:mm:ss格式,如：2023-01-01 00:00:00,2027-01-31 23:59:59")
    private LocalDateTime[] timeRange ;

    @Schema(description = "栋号")
    private String dormBuilding ;


}