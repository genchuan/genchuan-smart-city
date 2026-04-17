package cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingreport.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description = "汽车充电 - 分账报表分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class SharingReportCustomCreateReqVO extends PageParam {

    @Schema(description = "报表名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String reportName;

    @Schema(description = "时间结束", requiredMode = Schema.RequiredMode.REQUIRED, example = "2025-03-01 00:00:00")
    private Long timeStart;

    @Schema(description = "时间结束", requiredMode = Schema.RequiredMode.REQUIRED, example = "2025-03-01 00:00:00")
    private Long timeEnd;

    @Schema(description = "合作方", example = "XX 能源公司")
    private String cooperator;
}