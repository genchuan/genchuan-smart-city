package cn.iocoder.yudao.module.waterdetection.controller.admin.meteruserrelation.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 户表关联及变更管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MeterUserRelationPageReqVO extends PageParam {

    @Schema(description = "户表编号")
    private String meterCode;

    @Schema(description = "原用户编号")
    private String oldUserCode;

    @Schema(description = "新用户编号")
    private String newUserCode;

    @Schema(description = "变更原因")
    private String changeReason;

    @Schema(description = "变更时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] changeTime;

    @Schema(description = "经办人")
    private String operator;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}