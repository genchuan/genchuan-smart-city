package cn.iocoder.yudao.module.facility.controller.admin.road.roadconfig.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 道路监测配置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class RoadConfigRespVO {

    @Schema(description = "[主键ID] 主键，道路监测配置唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "7754")
    @ExcelProperty("[主键ID] 主键，道路监测配置唯一标识")
    private Long id;

    @Schema(description = "[配置编码] UUID格式", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[配置编码] UUID格式")
    private String configCode;

    @Schema(description = "[配置名称]", example = "赵六")
    @ExcelProperty("[配置名称]")
    private String name;

    @Schema(description = "[道路ID] 关联road_facility.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "3554")
    @ExcelProperty("[道路ID] 关联road_facility.id")
    private Long roadId;

    @Schema(description = "[道路名称]", example = "芋艿")
    @ExcelProperty("[道路名称]")
    private String roadName;

    @Schema(description = "[采集频率] 数据采集频率，单位：分钟", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[采集频率] 数据采集频率，单位：分钟")
    private Integer collectFrequency;

    @Schema(description = "[坑洼数量阈值] 坑洼数量阈值")
    @ExcelProperty("[坑洼数量阈值] 坑洼数量阈值")
    private BigDecimal potholeNumThreshold;

    @Schema(description = "[裂缝长度阈值] 裂缝长度阈值，单位：米")
    @ExcelProperty("[裂缝长度阈值] 裂缝长度阈值，单位：米")
    private BigDecimal crackLengthThreshold;

    @Schema(description = "[路面温度阈值] 路面温度阈值，单位：摄氏度")
    @ExcelProperty("[路面温度阈值] 路面温度阈值，单位：摄氏度")
    private BigDecimal roadTempThreshold;

    @Schema(description = "[交通流量阈值] 交通流量阈值，单位：辆/小时")
    @ExcelProperty("[交通流量阈值] 交通流量阈值，单位：辆/小时")
    private BigDecimal trafficFlowThreshold;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[通用扩展字段1] 通用扩展字段1")
    @ExcelProperty("[通用扩展字段1] 通用扩展字段1")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 通用扩展字段2")
    @ExcelProperty("[通用扩展字段2] 通用扩展字段2")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 通用扩展字段3")
    @ExcelProperty("[通用扩展字段3] 通用扩展字段3")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 通用扩展字段4")
    @ExcelProperty("[通用扩展字段4] 通用扩展字段4")
    private String extCommon4;

}
