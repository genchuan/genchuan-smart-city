package cn.iocoder.yudao.module.stationresource.api.station.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 场站信息 RPC 响应 DTO。坐标来自 area_info(片区级粗坐标)。
 */
@Schema(description = "RPC - 场站信息响应")
@Data
public class StationInfoRespDTO {

    @Schema(description = "场站 ID")
    private Long id;

    @Schema(description = "场站名称")
    private String name;

    @Schema(description = "场站地址")
    private String address;

    @Schema(description = "泊位总数")
    private Integer spaceTotal;

    // TODO spaceCount 字段语义待业务方最终确认:DO 注释"车位绑定数",当前按"已占用数"消费(emptySpace=spaceTotal-spaceCount)
    @Schema(description = "已占用车位数(口径见注释)")
    private Integer spaceCount;

    @Schema(description = "片区 ID,坐标关联字段")
    private Long areaId;

    @Schema(description = "经度(来自 area_info)")
    private BigDecimal lon;

    @Schema(description = "纬度(来自 area_info)")
    private BigDecimal lat;

    @Schema(description = "空位数(服务端按 spaceTotal-spaceCount 算好,调用方直接用,避免口径分叉)")
    private Integer emptySpace;

}
