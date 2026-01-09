// DataOverviewQueryReqVO.java
package cn.iocoder.yudao.module.industry.controller.admin.businessservices.dpzl.dataview.vo;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 数据概览查询 Request VO")
@Data
public class BusiDataViewQueryReqVO {

    @Schema(description = "区域编码", example = "330100")
    private String regionCode;

    @Schema(description = "统计周期", example = "DAY")
    private String statCycle;

    @Schema(description = "开始时间")
    private LocalDateTime beginTime;

    @Schema(description = "结束时间")
    private LocalDateTime endTime;

}