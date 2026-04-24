package cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.spacequery.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 泊位查询统计 Request VO")
@Data
public class SpaceQueryChartReqVO {

    @Schema(description = "片区 ID", example = "1")
    private Long areaId;

}