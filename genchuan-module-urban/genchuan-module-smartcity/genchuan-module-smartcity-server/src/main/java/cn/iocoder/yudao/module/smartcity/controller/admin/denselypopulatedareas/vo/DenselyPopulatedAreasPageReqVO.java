package cn.iocoder.yudao.module.smartcity.controller.admin.denselypopulatedareas.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 人员密集场所安全分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DenselyPopulatedAreasPageReqVO extends PageParam {

    @Schema(description = "所名称", example = "芋艿")
    private String theName;

    @Schema(description = "场所类型")
    private String typeOfVenue;

    @Schema(description = "地址")
    private String address;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}