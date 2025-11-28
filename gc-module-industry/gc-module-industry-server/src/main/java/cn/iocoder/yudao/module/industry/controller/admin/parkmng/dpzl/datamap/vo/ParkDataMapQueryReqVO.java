package cn.iocoder.yudao.module.industry.controller.admin.parkmng.dpzl.datamap.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "管理后台 - 停车数据地图查询 Request VO")
@Data
@EqualsAndHashCode
@ToString
public class ParkDataMapQueryReqVO {

    @Schema(description = "停车场ID")
    private Long parkId;

    @Schema(description = "停车场名称")
    private String parkName;

    @Schema(description = "停车场类型")
    private String parkType;

    @Schema(description = "泊位状态")
    private String spaceStatus;

    @Schema(description = "故障标识")
    private Boolean faultFlag;

}