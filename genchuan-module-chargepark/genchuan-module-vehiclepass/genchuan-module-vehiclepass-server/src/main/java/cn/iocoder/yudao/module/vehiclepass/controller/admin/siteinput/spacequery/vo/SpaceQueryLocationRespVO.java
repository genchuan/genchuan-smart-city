package cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.spacequery.vo;

import lombok.*;
import java.math.BigDecimal;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "管理后台 - 泊位查询定位 Response VO")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpaceQueryLocationRespVO {

    @Schema(description = "经度", example = "116.397428")
    private BigDecimal lon;

    @Schema(description = "纬度", example = "39.90923")
    private BigDecimal lat;

    @Schema(description = "泊位名称", example = "A001")
    private String spaceName;

    @Schema(description = "片区名称", example = "东区")
    private String areaName;

}