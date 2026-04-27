package cn.iocoder.yudao.module.inspectop.controller.admin.cyclereport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "巡查巡检 - 生成巡检运维报表 Response VO")
@Data
public class CycleReportGenerateRespVO {

    @Schema(description = "生成的报表 ID", example = "49")
    private Long id;

    @Schema(description = "生成状态（生成中 / 已生成）", example = "已生成")
    private String generateStatus;
}