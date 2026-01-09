// ServiceItemsQueryReqVO.java
package cn.iocoder.yudao.module.industry.controller.admin.businessservices.dpzl.serviceitems.vo;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 服务事项统计查询 Request VO")
@Data
public class BusiServiceItemsQueryReqVO {

    @Schema(description = "统计周期")
    private String statCycle;

    @Schema(description = "统计时间")
    private LocalDateTime statTime;

}