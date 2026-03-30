package cn.iocoder.yudao.module.facility.controller.admin.road.roadconfig.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 道路监测配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RoadConfigPageReqVO extends PageParam {

    @Schema(description = "[配置编码] UUID格式")
    private String configCode;

    @Schema(description = "[配置名称]", example = "赵六")
    private String name;

    @Schema(description = "[道路ID] 关联road_facility.id", example = "3554")
    private Long roadId;

    @Schema(description = "[道路名称]", example = "芋艿")
    private String roadName;

    @Schema(description = "[采集频率] 数据采集频率，单位：分钟")
    private Integer collectFrequency;

    @Schema(description = "[坑洼数量阈值] 坑洼数量阈值")
    private BigDecimal potholeNumThreshold;

    @Schema(description = "[裂缝长度阈值] 裂缝长度阈值，单位：米")
    private BigDecimal crackLengthThreshold;

    @Schema(description = "[路面温度阈值] 路面温度阈值，单位：摄氏度")
    private BigDecimal roadTempThreshold;

    @Schema(description = "[交通流量阈值] 交通流量阈值，单位：辆/小时")
    private BigDecimal trafficFlowThreshold;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[通用扩展字段1] 通用扩展字段1")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 通用扩展字段2")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 通用扩展字段3")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 通用扩展字段4")
    private String extCommon4;

}
