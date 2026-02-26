package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferreserve;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 进站预约新增/修改 Request VO")
@Data
public class TransferReserveSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "27300")
    private Long id;

    @Schema(description = "预约主键（UUID）", example = "15220")
    private String reserveId;

    @Schema(description = "关联sys_vehicle.id", example = "1744")
    private String vehicleId;

    @Schema(description = "关联sys_garbage_type.id", example = "9608")
    private String garbageTypeId;

    @Schema(description = "预计进站时间")
    private LocalDateTime expectedTime;

    @Schema(description = "垃圾重量（单位：吨）")
    private BigDecimal garbageWeight;

    @Schema(description = "关联sys_area.area_code")
    private String areaCode;

    @Schema(description = "预约状态：待排序/已排序/已进站", example = "1")
    private String reserveStatus;

    @Schema(description = "排序序号")
    private Integer sortNo;

    @Schema(description = "创建时间（业务字段）")
    private LocalDateTime abnormalCreateTime;

    @Schema(description = "关联sys_user.id")
    private String handleBy;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}