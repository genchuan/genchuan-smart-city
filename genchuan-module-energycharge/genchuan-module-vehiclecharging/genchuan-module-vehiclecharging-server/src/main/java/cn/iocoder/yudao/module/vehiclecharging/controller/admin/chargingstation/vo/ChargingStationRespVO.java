package cn.iocoder.yudao.module.vehiclecharging.controller.admin.chargingstation.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 充电站分页 Response VO")
@Data
public class ChargingStationRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "1001")
    private Long id;

    @Schema(description = "区域编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1001")
    private Long areaId;

    @Schema(description = "场站编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "CS20250301001")
    private String stationCode;

    @Schema(description = "场站名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "泉州丰泽万达广场充电站")
    private String stationName;

    @Schema(description = "场站地址", example = "福建省泉州市丰泽区")
    private String address;

    @Schema(description = "合作模式", example = "self")
    private String coopMode;

    @Schema(description = "开放时间", example = "00:00-24:00")
    private String openTime;

    @Schema(description = "电价服务费", example = "0.58")
    private BigDecimal priceService;

    @Schema(description = "负责人", example = "张三")
    private String manager;

    @Schema(description = "场站状态", example = "enabled")
    private String stationStatus;

    @Schema(description = "经度", example = "118.58942")
    private BigDecimal lon;

    @Schema(description = "纬度", example = "24.90735")
    private BigDecimal lat;

    @Schema(description = "停用原因")
    private String stopReason;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "创建者", example = "1")
    private String creator;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime updateTime;

}