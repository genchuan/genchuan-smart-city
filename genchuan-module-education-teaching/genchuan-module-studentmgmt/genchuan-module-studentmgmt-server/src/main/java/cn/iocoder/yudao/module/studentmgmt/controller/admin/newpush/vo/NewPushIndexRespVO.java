package cn.iocoder.yudao.module.studentmgmt.controller.admin.newpush.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 推送核心指标统计 response VO")
@Data
public class NewPushIndexRespVO {

    @Schema(description = "总推送任务数")
    private Integer totalTaskCount;
    @Schema(description = "已完成推送任务数")
    private Integer pushedCount;
    @Schema(description = "推送任务完成率")
    private BigDecimal pushRate;
    @Schema(description = "累计推送人数")
    private Integer totalPushNum;

}