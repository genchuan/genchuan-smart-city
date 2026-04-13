package cn.iocoder.yudao.module.vehiclecharging.controller.admin.sysarea.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "汽车充电 - 统一行政区划配置表（树形结构）分页 Request VO")
@Data
public class AreaPageReqVO extends PageParam {

    @Schema(description = "上级行政区划ID（0表示根节点）", example = "27543")
    private Long parentId;

    @Schema(description = "完整行政区划代码（12位）")
    private String fullCode;

    @Schema(description = "短代码（省/市/县6位，乡镇/社区3位）")
    private String shortCode;

    @Schema(description = "社区ID", example = "12571")
    private String commId;

    @Schema(description = "行政区划名称", example = "张三")
    private String name;

    @Schema(description = "层级：1-省级 2-市级 3-县级 4-乡镇 5-社区")
    private Integer level;

    @Schema(description = "类型（街道/镇/乡/社区/村）", example = "1")
    private String areaType;

    @Schema(description = "生效时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] effectiveTime;

    @Schema(description = "失效时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] invalidTime;

    @Schema(description = "社区边界坐标（2000坐标系）")
    private String boundary;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}