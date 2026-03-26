package cn.iocoder.yudao.module.waterdetection.controller.admin.testingcapability.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 检测能力及设备管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TestingCapabilityPageReqVO extends PageParam {

    @Schema(description = "机构编号")
    private String agencyCode;

    @Schema(description = "可检测指标")
    private String testableIndicators;

    @Schema(description = "设备型号")
    private String equipmentModel;

    @Schema(description = "设备编号")
    private String equipmentNo;

    @Schema(description = "校准记录")
    private String calibrationRecord;

    @Schema(description = "设备状态(正常/维修中/停用)")
    private String equipmentStatus;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}