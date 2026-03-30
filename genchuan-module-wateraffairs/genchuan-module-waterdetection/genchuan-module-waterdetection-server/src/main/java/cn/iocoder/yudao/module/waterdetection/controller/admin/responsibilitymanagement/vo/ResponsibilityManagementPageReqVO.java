package cn.iocoder.yudao.module.waterdetection.controller.admin.responsibilitymanagement.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 责任单位及责任人管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ResponsibilityManagementPageReqVO extends PageParam {

    @Schema(description = "责任类型(主体责任/监管责任/运行管理责任)")
    private String responsibilityType;

    @Schema(description = "责任单位")
    private String responsibleUnit;

    @Schema(description = "责任人姓名")
    private String responsiblePerson;

    @Schema(description = "职务")
    private String position;

    @Schema(description = "联系方式")
    private String contactInfo;

    @Schema(description = "责任范围")
    private String responsibilityScope;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}