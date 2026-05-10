package cn.iocoder.yudao.module.usermerchant.controller.admin.userreport.cyclereport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 商户周期报表生成 Response VO")
@Data
public class CycleReportGenerateRespVO {

    @Schema(description = "生成的报表ID", example = "1")
    private Long id;

    @Schema(description = "生成状态（待生成/已生成/生成失败）", example = "已生成")
    private String generateStatus;
}