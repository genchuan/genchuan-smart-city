package cn.iocoder.yudao.module.smartcity.controller.admin.environmentalsafety.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 环卫设施安全分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EnvironmentalSafetyPageReqVO extends PageParam {

    @Schema(description = "设施名称", example = "芋艿")
    private String facilityName;

    @Schema(description = "设施类型", example = "2")
    private String facilityType;

    @Schema(description = "设施位置")
    private String facilityLocation;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}