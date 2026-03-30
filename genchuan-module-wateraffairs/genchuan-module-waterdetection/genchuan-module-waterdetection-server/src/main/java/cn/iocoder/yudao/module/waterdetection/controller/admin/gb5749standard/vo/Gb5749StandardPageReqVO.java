package cn.iocoder.yudao.module.waterdetection.controller.admin.gb5749standard.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 《生活饮用水卫生标准》GB 5749-2022标准分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Gb5749StandardPageReqVO extends PageParam {

    @Schema(description = "指标名称")
    private String itemName;

    @Schema(description = "标准值")
    private String limitValue;

    @Schema(description = "排序序号")
    private Integer itemOrder;

}