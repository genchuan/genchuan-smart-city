package cn.iocoder.yudao.module.smartcity.controller.admin.drainageuser.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 排水户信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DrainageUserPageReqVO extends PageParam {

    @Schema(description = "统一社会信用代码", example = "企业唯一标识")
    private String creditCode;

    @Schema(description = "排水户名称", example = "商户注册全称")
    private String userName;

    @Schema(description = "行业类别")
    private String industryType;

}