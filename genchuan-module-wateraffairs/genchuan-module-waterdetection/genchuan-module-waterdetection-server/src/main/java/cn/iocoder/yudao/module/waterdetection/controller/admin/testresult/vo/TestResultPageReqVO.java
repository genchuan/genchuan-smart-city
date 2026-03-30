package cn.iocoder.yudao.module.waterdetection.controller.admin.testresult.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 检测结果录入分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TestResultPageReqVO extends PageParam {

    @Schema(description = "样本编号")
    private String sampleCode;

    @Schema(description = "检测指标")
    private String testIndicator;

    @Schema(description = "检测值")
    private Double testValue;

    @Schema(description = "单位")
    private String unit;

    @Schema(description = "检测方法")
    private String testMethod;

    @Schema(description = "检测人员")
    private String testOperator;

    @Schema(description = "检测时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] testTime;

    @Schema(description = "设备编号")
    private String equipmentCode;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}