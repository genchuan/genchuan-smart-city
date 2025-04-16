package cn.iocoder.yudao.module.smartcity.controller.admin.bridgesafety.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 桥梁安全分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BridgeSafetyPageReqVO extends PageParam {

    @Schema(description = "桥梁名称", example = "王五")
    private String bridgeName;

    @Schema(description = "桥梁编号")
    private String bridgeNumber;

    @Schema(description = "桥梁位置")
    private String bridgeLocation;

    @Schema(description = "桥梁类型", example = "1")
    private String bridgeType;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}