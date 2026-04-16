package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.marketopreport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 营销运营报表 Response VO")
@Data
public class MarketOpReportRespVO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "报表名称")
    private String name;

    @Schema(description = "报表类型")
    private String type;

    @Schema(description = "统计开始时间")
    private LocalDateTime startTime;

    @Schema(description = "统计结束时间")
    private LocalDateTime endTime;

    @Schema(description = "报表状态")
    private String status;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

}
