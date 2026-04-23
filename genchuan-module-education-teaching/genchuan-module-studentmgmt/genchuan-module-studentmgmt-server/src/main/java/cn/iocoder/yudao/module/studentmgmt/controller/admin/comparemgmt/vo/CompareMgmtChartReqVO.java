package cn.iocoder.yudao.module.studentmgmt.controller.admin.comparemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 德育评比排名看板 Request VO")
@Data
public class CompareMgmtChartReqVO {

    @Schema(description = "评比周期（周 / 月 / 学期）", requiredMode = Schema.RequiredMode.REQUIRED, example = "week")
    private String cycle;

}