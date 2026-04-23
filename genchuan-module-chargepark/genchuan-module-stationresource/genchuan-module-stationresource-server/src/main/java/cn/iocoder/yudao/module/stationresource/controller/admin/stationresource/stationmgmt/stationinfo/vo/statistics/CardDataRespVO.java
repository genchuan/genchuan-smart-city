package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationinfo.vo.statistics;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "场站统计卡片数据")
public class CardDataRespVO {
//
    @Schema(description = "总站数", example = "120")
    private Long totalStationCount;

    @Schema(description = "正常运营数（已生效）", example = "105")
    private Long normalOperateCount;
}