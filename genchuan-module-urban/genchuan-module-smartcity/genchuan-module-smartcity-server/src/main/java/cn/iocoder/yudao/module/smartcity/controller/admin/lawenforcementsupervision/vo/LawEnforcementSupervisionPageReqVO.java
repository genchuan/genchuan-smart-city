package cn.iocoder.yudao.module.smartcity.controller.admin.lawenforcementsupervision.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 执法监督分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LawEnforcementSupervisionPageReqVO extends PageParam {

    @Schema(description = "监督编号", example = "11753")
    private String supervisionId;

    @Schema(description = "执法事件编号")
    private String eventNumber;

    @Schema(description = "执法人员")
    private String officials;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}