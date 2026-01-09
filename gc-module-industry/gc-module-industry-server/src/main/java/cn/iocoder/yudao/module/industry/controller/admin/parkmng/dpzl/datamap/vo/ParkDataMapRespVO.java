package cn.iocoder.yudao.module.industry.controller.admin.parkmng.dpzl.datamap.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 停车数据地图 Response VO")
@Data
@EqualsAndHashCode
@ToString
public class ParkDataMapRespVO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "停车场ID")
    private Long parkId;

    @Schema(description = "停车场名称")
    private String parkName;

    @Schema(description = "泊位总数")
    private Integer totalSpace;

    @Schema(description = "空闲泊位数")
    private Integer freeSpace;

    @Schema(description = "泊位状态")
    private String spaceStatus;

    @Schema(description = "坐标X")
    private BigDecimal coordX;

    @Schema(description = "坐标Y")
    private BigDecimal coordY;

    @Schema(description = "停车场类型")
    private String parkType;

    @Schema(description = "泊位位置")
    private String spaceLocation;

    @Schema(description = "故障标识")
    private Boolean faultFlag;

    @Schema(description = "周边空闲泊位")
    private Integer surroundFreeSpace;

    @Schema(description = "待疏散车辆数")
    private Integer toEvacVehicle;

}