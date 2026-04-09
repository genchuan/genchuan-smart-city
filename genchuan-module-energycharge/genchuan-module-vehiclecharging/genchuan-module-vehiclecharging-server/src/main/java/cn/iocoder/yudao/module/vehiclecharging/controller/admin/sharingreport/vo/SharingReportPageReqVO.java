package cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingreport.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description = "管理后台 - 分账报表分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class SharingReportPageReqVO extends PageParam {

    @Schema(description = "报表类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "月")
    private String reportType;

    @Schema(description = "报表时间范围", requiredMode = Schema.RequiredMode.REQUIRED, example = "2025-03")
    private String timeRange;

    @Schema(description = "合作方", example = "XX 能源公司")
    private String cooperator;
}