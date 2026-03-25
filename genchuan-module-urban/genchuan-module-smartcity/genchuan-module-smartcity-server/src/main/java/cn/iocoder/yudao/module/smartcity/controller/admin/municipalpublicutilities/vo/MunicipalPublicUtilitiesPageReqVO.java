package cn.iocoder.yudao.module.smartcity.controller.admin.municipalpublicutilities.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 市政公用分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MunicipalPublicUtilitiesPageReqVO extends PageParam {

    @Schema(description = "市政设施名称")
    private String nameMunicipalFacilities;

    @Schema(description = "设施类型", example = "2")
    private String facilityType;

    @Schema(description = "设施位置")
    private String facilityLocation;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}