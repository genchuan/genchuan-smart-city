package cn.iocoder.yudao.module.smartcity.controller.admin.inspectionobject.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 双随机行政检查分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InspectionObjectPageReqVO extends PageParam {

    @Schema(description = "企业名称", example = "赵六")
    private String entName;

    @Schema(description = "统一社会信用代码")
    private String creditCode;

    @Schema(description = "法定代表人")
    private String legalPerson;

}