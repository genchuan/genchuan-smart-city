package cn.iocoder.yudao.module.vehiclecharging.controller.admin.charginglot.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 充电车位新增/修改 Request VO")
@Data
public class ChargingLotSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "车位编号")
    private String lotCode;

    @Schema(description = "所属场站编码")
    private String stationCode;

    @Schema(description = "所属场站ID")
    private Long stationId;

    @Schema(description = "车位类型")
    private String lotType;

    @Schema(description = "关联充电桩编号")
    private String pileCode;

    @Schema(description = "关联充电桩ID")
    private Long pileId;

    @Schema(description = "占用时长（分钟）")
    private Integer occupyTime;

    @Schema(description = "车位状态")
    private String lotStatus;

    @Schema(description = "占用超时时间（分钟）")
    private Integer occupyTimeout;

    @Schema(description = "维护原因")
    private String maintainReason;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}