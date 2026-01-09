package cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkrealtime.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 停车泊位实时状态新增/修改 Request VO")
@Data
public class ParkRealTimeSaveReqVO {

    @Schema(description = "主键，唯一标识", example = "5784")
    private Long id;

    @Schema(description = "推荐停车场唯一标识", example = "4718")
    private String recommendParkId;

    @Schema(description = "当前剩余可用泊位数量")
    private Integer freeSpace;

    @Schema(description = "泊位占用比例（百分比）")
    private BigDecimal occupyRate;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}
