package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 积分活动精简信息 Response VO")
@Data
public class PointActivitySimpleRespVO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "活动名称")
    private String name;

}
