package cn.iocoder.yudao.module.facility.controller.admin.road.roadconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 道路监测配置新增/修改 Request VO")
@Data
public class RoadConfigSaveReqVO {

//    @Schema(description = "[主键ID] 主键，道路监测配置唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
//    private Long id;

//    @Schema(description = "[配置编码] UUID格式", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotEmpty(message = "[配置编码] UUID格式不能为空")
    @Schema(hidden = true)
    private String configCode;

    @Schema(description = "[配置名称]", example = "五四路配置")
    private String name;

    @Schema(description = "[道路ID] 关联road_facility.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "[道路ID] 关联road_facility.id不能为空")
    private Long roadId;

    @Schema(description = "[道路名称]", example = "五四路")
    private String roadName;

    @Schema(description = "[采集频率] 数据采集频率，单位：分钟", requiredMode = Schema.RequiredMode.REQUIRED,example = "10")
    @NotNull(message = "[采集频率] 数据采集频率，单位：分钟不能为空")
    private Integer collectFrequency;

    @Schema(description = "[坑洼数量阈值] 坑洼数量阈值", requiredMode = Schema.RequiredMode.REQUIRED,example = "10")
    private BigDecimal potholeNumThreshold;

    @Schema(description = "[裂缝长度阈值] 裂缝长度阈值，单位：米", requiredMode = Schema.RequiredMode.REQUIRED,example = "10")
    private BigDecimal crackLengthThreshold;

    @Schema(description = "[路面温度阈值] 路面温度阈值，单位：摄氏度", requiredMode = Schema.RequiredMode.REQUIRED,example = "10")
    private BigDecimal roadTempThreshold;

    @Schema(description = "[交通流量阈值] 交通流量阈值，单位：辆/小时", requiredMode = Schema.RequiredMode.REQUIRED,example = "110")
    private BigDecimal trafficFlowThreshold;

//    @Schema(description = "[通用扩展字段1] 通用扩展字段1")
//    private String extCommon1;
//
//    @Schema(description = "[通用扩展字段2] 通用扩展字段2")
//    private String extCommon2;
//
//    @Schema(description = "[通用扩展字段3] 通用扩展字段3")
//    private String extCommon3;
//
//    @Schema(description = "[通用扩展字段4] 通用扩展字段4")
//    private String extCommon4;

}
