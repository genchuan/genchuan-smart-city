package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationinfo.vo.statistics;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "各类型场站数量统计")
public class TypeCountBarRespVO {

    @Schema(description = "类型名称", example = "商业")
    private String name;

    @Schema(description = "数量", example = "45")
    private Long value;
}
