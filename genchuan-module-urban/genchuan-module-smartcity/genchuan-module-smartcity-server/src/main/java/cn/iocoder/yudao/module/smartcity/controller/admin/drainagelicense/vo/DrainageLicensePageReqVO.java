package cn.iocoder.yudao.module.smartcity.controller.admin.drainagelicense.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 排水电子许可证信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DrainageLicensePageReqVO extends PageParam {

    @Schema(description = "许可证编号")
    private String licenseNo;

    @Schema(description = "许可排水类型", example = "2")
    private String drainageType;

    @Schema(description = "审批单位")
    private String approvalUnit;

}