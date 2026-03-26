package cn.iocoder.yudao.module.smartcity.controller.admin.caseinvestigation.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 案件调查分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CaseInvestigationPageReqVO extends PageParam {

    @Schema(description = "案件ID", example = "29928")
    private String caseId;

    @Schema(description = "调查结果")
    private String investigationResult;

}