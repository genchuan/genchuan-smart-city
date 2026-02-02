package cn.iocoder.yudao.module.park.controller.admin.park.resource.inputcar.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 泊位录入车辆新增/修改 Request VO")
@Data
public class ParkInputCarSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25588")
    private Long id;

    @Schema(description = "目标泊位号")
    private String targetBerthNo;

    @Schema(description = "车牌号码")
    private String carNumber;

    @Schema(description = "车辆类型", example = "2")
    private String carType;

    @Schema(description = "车牌颜色")
    private String plateColor;

    @Schema(description = "停车状态", example = "1")
    private String parkingStatus;

    @Schema(description = "入场时间")
    private LocalDateTime entryTime;

    @Schema(description = "出场时间")
    private LocalDateTime exitTime;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}