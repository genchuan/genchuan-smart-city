// DataMapQueryReqVO.java
package cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.datamap.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CultureDataMapQueryReqVO {
    @Schema(description = "区域编码")
    private String regionCode;

    @Schema(description = "统计周期，格式:YYYY/YYYYQn/YYYYMM")
    private String statCycle;

    // 可根据需要添加更多查询参数
}