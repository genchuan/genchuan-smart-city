package cn.iocoder.yudao.module.smartcity.controller.admin.classificationofpoliciesandregulations.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 政策法规分类分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ClassificationOfPoliciesAndRegulationsPageReqVO extends PageParam {

    @Schema(description = "法规类别")
    private String regulatoryCategory;

    @Schema(description = "所属领域")
    private String isArea;

    @Schema(description = "主题")
    private String theme;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}