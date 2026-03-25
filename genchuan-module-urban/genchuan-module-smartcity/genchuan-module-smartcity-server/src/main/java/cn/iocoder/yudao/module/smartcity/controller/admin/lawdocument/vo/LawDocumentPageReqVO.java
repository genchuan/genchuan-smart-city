package cn.iocoder.yudao.module.smartcity.controller.admin.lawdocument.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 执法文书分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LawDocumentPageReqVO extends PageParam {

    @Schema(description = "案件ID", example = "9444")
    private String caseId;

    @Schema(description = "文书类型", example = "1")
    private String documentType;

    @Schema(description = "审批时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] approvalTime;

    @Schema(description = "审批状态", example = "2")
    private String approvalStatus;

}