package cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.passengerflow.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "管理后台 - 文旅客流总览视图查询参数")
public class CulturePasFlowQueryReqVO {

    @Schema(description = "日期类型（today-今日 yesterday-昨日 weekend-周末）", example = "today")
    private String dateType;

    @Schema(description = "行政区域编码", example = "110101")
    private String regionCode;

    @Schema(description = "客流类型（景区/场馆/活动）", example = "景区")
    private String catName;
}