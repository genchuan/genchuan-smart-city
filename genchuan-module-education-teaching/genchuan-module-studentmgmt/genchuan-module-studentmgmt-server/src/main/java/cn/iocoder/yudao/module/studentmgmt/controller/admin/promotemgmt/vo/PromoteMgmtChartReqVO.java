package cn.iocoder.yudao.module.studentmgmt.controller.admin.promotemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 招生宣传统计看板 Request VO")
@Data
public class PromoteMgmtChartReqVO {

    @Schema(description = "学年", requiredMode = Schema.RequiredMode.REQUIRED, example = "2025")
    private Integer year;


}