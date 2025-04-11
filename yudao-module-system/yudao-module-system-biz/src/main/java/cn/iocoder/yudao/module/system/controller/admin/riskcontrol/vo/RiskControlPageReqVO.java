package cn.iocoder.yudao.module.system.controller.admin.riskcontrol.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 风险管控分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RiskControlPageReqVO extends PageParam {

    @Schema(description = "风险名称", example = "王五")
    private String riskName;

    @Schema(description = "风险的详细描述", example = "你猜")
    private String riskDescription;

    @Schema(description = "风险等级")
    private String riskLevel;

    @Schema(description = "风险状态", example = "2")
    private String riskStatus;

}