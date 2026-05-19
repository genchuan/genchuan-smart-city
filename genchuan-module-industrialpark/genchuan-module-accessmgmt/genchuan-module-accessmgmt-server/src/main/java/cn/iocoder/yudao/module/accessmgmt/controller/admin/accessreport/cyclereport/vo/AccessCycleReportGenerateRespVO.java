package cn.iocoder.yudao.module.accessmgmt.controller.admin.accessreport.cyclereport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 通行周期报表生成 Response VO")
@Data
public class AccessCycleReportGenerateRespVO {

    @Schema(description = "是否成功")
    private Boolean success;

    @Schema(description = "报表ID")
    private Long reportId;

}
