package cn.iocoder.yudao.module.datacenter.controller.admin.emerplan.emerresstatus.vo;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 资源状态统计分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EmerResStatusPageReqVO extends PageParam {

    @Schema(description = "统计ID", example = "26864")
    private String statId;

    @Schema(description = "统计周期")
    private String statCycle;

    @Schema(description = "统计时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] statTime;

    @Schema(description = "资源类型", example = "1")
    private String resType;

    @Schema(description = "行政区划代码")
    private String regionCode;

    @Schema(description = "行政区划名称", example = "张三")
    private String regionName;

    @Schema(description = "总数量")
    private Integer totalQuantity;

    @Schema(description = "可用数量")
    private Integer availableQuantity;

    @Schema(description = "异常数量")
    private Integer abnQuantity;

}