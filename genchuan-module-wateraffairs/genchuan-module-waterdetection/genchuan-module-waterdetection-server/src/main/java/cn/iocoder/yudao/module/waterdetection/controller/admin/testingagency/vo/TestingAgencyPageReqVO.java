package cn.iocoder.yudao.module.waterdetection.controller.admin.testingagency.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 检测机构资质管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TestingAgencyPageReqVO extends PageParam {

    @Schema(description = "机构编号")
    private String agencyCode;

    @Schema(description = "机构名称")
    private String agencyName;

    @Schema(description = "资质证书编号")
    private String certificateNo;

    @Schema(description = "检测范围")
    private String testingScope;

    @Schema(description = "有效期至")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] validDate;

    @Schema(description = "发证单位")
    private String issuingAuthority;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}