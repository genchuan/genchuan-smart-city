package cn.iocoder.yudao.module.smartcity.controller.admin.policyandregulationinput.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 政策法规录入分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PolicyAndRegulationInputPageReqVO extends PageParam {

    @Schema(description = "制定机关")
    private String developingAgencies;

    @Schema(description = "政策法规名称")
    private String nameOfPolicyAndRegulation;

    @Schema(description = "文号")
    private String documentNumber;

    @Schema(description = "正文内容")
    private String mainContent;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}